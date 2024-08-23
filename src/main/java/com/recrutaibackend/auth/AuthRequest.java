package com.recrutaibackend.auth;

import jakarta.validation.constraints.NotEmpty;

public record AuthRequest(
        @NotEmpty
        String email,

        @NotEmpty
        String password
) {
}
