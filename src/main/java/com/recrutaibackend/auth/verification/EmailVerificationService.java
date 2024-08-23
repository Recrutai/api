package com.recrutaibackend.auth.verification;

import com.recrutaibackend.auth.user.User;
import com.recrutaibackend.notification.email.Email;
import com.recrutaibackend.notification.NotificationService;
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

    public EmailVerification create(User user) {
        var now = Instant.now();
        var emailVerification = new EmailVerification(
                generateVerificationCode(),
                now,
                now.plus(VERIFICATION_CODE_VALIDITY),
                user
        );
        return emailVerificationRepository.save(emailVerification);
    }

    public void send(User user, String verificationCode) {
        var verificationEmail = buildMessage(user, verificationCode);
        notificationService.send(verificationEmail);
    }

    public void verify(EmailVerification emailVerification) {
        if (emailVerification.getConfirmedAt() != null || emailVerification.getExpiresAt().isBefore(Instant.now())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid verification code");
        }
        emailVerification.setConfirmedAt(Instant.now());
        emailVerificationRepository.save(emailVerification);
    }

    public EmailVerification findByCode(String verificationCode) {
        return emailVerificationRepository.findByCode(verificationCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid verification code"));
    }

    private Email buildMessage(User user, String verificationCode) {
        return new Email(
                "no-reply@recrutai.com",
                user.getEmail(),
                "Recrutaí - Verificação de Email",
                "Obrigado por iniciar o processo de criação de conta no Recrutaí. " +
                "Queremos ter certeza de que é realmente você.\n\n" +
                "Por favor, insira o seguinte código de verificação quando for solicitado.\n\n" +
                verificationCode +
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
}
