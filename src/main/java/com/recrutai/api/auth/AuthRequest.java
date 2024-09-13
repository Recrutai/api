package com.recrutai.api.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class AuthRequest {
    @NotEmpty
    @Schema(example = "john_smith@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private final String email;

    @NotEmpty
    @Schema(example = "Password#123", requiredMode = Schema.RequiredMode.REQUIRED)
    private final String password;

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
