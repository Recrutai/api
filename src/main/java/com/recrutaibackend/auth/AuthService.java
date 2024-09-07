package com.recrutaibackend.auth;

import com.recrutaibackend.auth.user.UserRequest;
import com.recrutaibackend.auth.user.UserResponse;
import com.recrutaibackend.auth.user.UserMapper;
import com.recrutaibackend.auth.user.UserService;
import com.recrutaibackend.auth.verification.EmailVerificationService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerificationService emailVerificationService;

    public AuthService(
            UserService userService,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder,
            EmailVerificationService emailVerificationService
    ) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.emailVerificationService = emailVerificationService;
    }

    @Transactional
    public void register(UserRequest request) {
        var user = userService.create(request);
        emailVerificationService.send(user);
    }

    public UserResponse authenticate(AuthRequest request) {
        var user = userService.findByEmail(request.email());
        if (!passwordEncoder.matches(request.password(), user.getHashedPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
        }
        if (!user.getIsActive()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account not verified");
        }
        return userMapper.mapToResponse(user);
    }

    @Transactional
    public void verifyAccount(String verificationCode) {
        var emailVerification = emailVerificationService.verify(verificationCode);
        userService.activateUser(emailVerification.getUser());
    }

    public void resendCode(String email) {
        var user = userService.findByEmail(email);
        emailVerificationService.send(user);
    }

}
