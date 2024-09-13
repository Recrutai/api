package com.recrutai.api.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record AuthRequest(
        @NotEmpty
        @Schema(example = "john_smith@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
        String email,

        @NotEmpty
        @Schema(example = "Password#123", requiredMode = Schema.RequiredMode.REQUIRED)
        String password
) {
}
