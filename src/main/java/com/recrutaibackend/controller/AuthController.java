package com.recrutaibackend.controller;

import com.recrutaibackend.dto.LoginRequest;
import com.recrutaibackend.dto.UserRequest;
import com.recrutaibackend.dto.UserResponse;
import com.recrutaibackend.service.AuthService;
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
    ResponseEntity<Void> registerUser(@RequestBody UserRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    ResponseEntity<UserResponse> logUserIn(@RequestBody LoginRequest loginRequest) {
        var user = authService.login(loginRequest);
        return ResponseEntity.ok(user);
    }

}
