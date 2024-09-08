package com.recrutaibackend.auth;

import com.recrutaibackend.auth.user.UserRequest;
import com.recrutaibackend.auth.user.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication")
public class AuthController {
    private final AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Authenticate the user using email and password")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content)
    })
    @PostMapping("/login")
    ResponseEntity<UserResponse> authenticate(@RequestBody @Valid AuthRequest request) {
        var user = authService.authenticate(request);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Register a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content)
    })
    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody @Valid UserRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Verify user's account email by code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/verify-account")
    ResponseEntity<Void> verifyAccount(@RequestParam @NotEmpty String code) {
        authService.verifyAccount(code);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Resend verification code to user's account email")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content)
    })
    @PostMapping("/resend-code")
    ResponseEntity<Void> resendCode(@RequestBody @NotEmpty String email) {
        authService.resendCode(email);
        return ResponseEntity.ok().build();
    }

}
