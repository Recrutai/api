package com.recrutai.api.auth.verification;

import com.recrutai.api.auth.user.User;
import com.recrutai.api.notification.NotificationService;
import com.recrutai.api.notification.email.Email;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;

@Service
public class EmailVerificationService {
    private static final int VERIFICATION_CODE_LENGTH = 6;
    private static final Duration VERIFICATION_CODE_VALIDITY = Duration.ofMinutes(10);

    private final EmailVerificationRepository emailVerificationRepository;
    private final NotificationService<Email> notificationService;

    public EmailVerificationService(
            EmailVerificationRepository emailVerificationRepository,
            NotificationService<Email> notificationService
    ) {
        this.emailVerificationRepository = emailVerificationRepository;
        this.notificationService = notificationService;
    }

    public void send(User user) {
        var emailVerification = create(user);
        var message = buildMessage(emailVerification);
        notificationService.send(message);
    }

    public EmailVerification verify(String verificationCode) {
        var emailVerification = findByCode(verificationCode);

        if (emailVerification.getConfirmedAt() != null || emailVerification.getExpiresAt().isBefore(Instant.now())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid verification code");
        }

        emailVerification.setConfirmedAt(Instant.now());

        return emailVerificationRepository.save(emailVerification);
    }

    private EmailVerification create(User user) {
        var now = Instant.now();
        var emailVerification = new EmailVerification(
                generateVerificationCode(),
                now,
                now.plus(VERIFICATION_CODE_VALIDITY),
                user
        );
        return emailVerificationRepository.save(emailVerification);
    }

    private Email buildMessage(EmailVerification emailVerification) {
        return new Email(
                "no-reply@recrutai.com",
                emailVerification.getUser().getEmail(),
                "Recrutaí - Verificação de Email",
                "Obrigado por iniciar o processo de criação de conta no Recrutaí. " +
                "Queremos ter certeza de que é realmente você.\n\n" +
                "Por favor, insira o seguinte código de verificação quando for solicitado.\n\n" +
                emailVerification.getCode() +
                "\n\nO código tem validade de 10 minutos. Se você não fez esta solicitação, por favor desconsidere este email." +
                "\n\nEquipe de Suporte do Recrutaí."
        );
    }

    private String generateVerificationCode() {
        var digits = "0123456789";
        var code = new StringBuilder(VERIFICATION_CODE_LENGTH);
        var secureRandom = new SecureRandom();
        for (int i = 0; i < VERIFICATION_CODE_LENGTH; i++) {
            var index = secureRandom.nextInt(digits.length());
            code.append(digits.charAt(index));
        }
        return code.toString();
    }

    private EmailVerification findByCode(String verificationCode) {
        return emailVerificationRepository.findWithUserByCode(verificationCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid verification code"));
    }

}
