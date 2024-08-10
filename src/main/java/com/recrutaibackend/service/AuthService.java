package com.recrutaibackend.service;

import com.recrutaibackend.dto.LoginRequest;
import com.recrutaibackend.dto.UserRequest;
import com.recrutaibackend.dto.UserResponse;
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
        var emailVerification = emailVerificationService.create(user);
        emailVerificationService.send(user, emailVerification.getCode());
    }

    public UserResponse login(LoginRequest request) {
        var user = userService.findUserByEmail(request.email());
        if (!passwordEncoder.matches(request.password(), user.getHashedPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
        }
        if(!user.getIsActive()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account not verified");
        }
        return userMapper.mapToResponse(user);
    }

    @Transactional
    public void verifyAccount(String verificationCode) {
        var emailVerification = emailVerificationService.findByCode(verificationCode);
        emailVerificationService.verify(emailVerification);
        userService.activateUser(emailVerification.getUser());
    }

    @Transactional
    public void resendVerifyCode(String email) {
        var user = userService.findUserByEmail(email);
        var emailVerification = emailVerificationService.create(user);
        emailVerificationService.send(user, emailVerification.getCode());
    }

}
