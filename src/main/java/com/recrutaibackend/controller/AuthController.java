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

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody @Valid UserRequest userRequest) {
        authService.register(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    ResponseEntity<UserResponse> authenticate(@RequestBody @Valid LoginRequest loginRequest) {
        var user = authService.authenticate(loginRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/verify-account")
    ResponseEntity<Void> verifyAccount(@RequestParam @NotEmpty String code) {
        authService.verifyAccount(code);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/resend-code")
    ResponseEntity<Void> resendCode(@RequestBody @NotEmpty String email) {
        authService.resendCode(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
