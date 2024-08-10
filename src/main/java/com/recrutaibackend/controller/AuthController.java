package com.recrutaibackend.controller;

import com.recrutaibackend.dto.LoginRequest;
import com.recrutaibackend.dto.UserRequest;
import com.recrutaibackend.dto.UserResponse;
import com.recrutaibackend.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    ResponseEntity<Void> registerUser(@RequestBody @Valid UserRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    ResponseEntity<UserResponse> logUserIn(@RequestBody @Valid LoginRequest loginRequest) {
        var user = authService.login(loginRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/verify-account")
    ResponseEntity<Void> verifyAccount(@RequestParam(name = "code") @NotEmpty String verificationCode) {
        authService.verifyAccount(verificationCode);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/resend-code")
    ResponseEntity<Void> resendCode(@RequestBody String email) {
        authService.resendVerifyCode(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
