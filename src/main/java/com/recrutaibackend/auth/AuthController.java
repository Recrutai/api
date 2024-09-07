package com.recrutaibackend.auth;

import com.recrutaibackend.auth.user.UserRequest;
import com.recrutaibackend.auth.user.UserResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    ResponseEntity<UserResponse> authenticate(@RequestBody @Valid AuthRequest request) {
        var user = authService.authenticate(request);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody @Valid UserRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/verify-account")
    ResponseEntity<Void> verifyAccount(@RequestParam @NotEmpty String code) {
        authService.verifyAccount(code);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/resend-code")
    ResponseEntity<Void> resendCode(@RequestBody @NotEmpty String email) {
        authService.resendCode(email);
        return ResponseEntity.ok().build();
    }

}
