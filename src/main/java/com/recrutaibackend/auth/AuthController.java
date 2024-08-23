package com.recrutaibackend.auth;

import com.recrutaibackend.auth.user.UserRequest;
import com.recrutaibackend.auth.user.UserResponse;
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
    ResponseEntity<UserResponse> authenticate(@RequestBody @Valid AuthRequest authRequest) {
        var user = authService.authenticate(authRequest);
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
        return ResponseEntity.ok().build();
    }

}
