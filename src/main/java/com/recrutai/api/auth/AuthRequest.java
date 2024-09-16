package com.recrutai.api.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AuthRequest {
    @NotEmpty
    @Size(min = 3, max = 255)
    @Pattern(regexp = "^(.+)@(\\S+)$")
    @Schema(example = "john_smith@email.com", requiredMode = RequiredMode.REQUIRED)
    private final String email;

    @NotEmpty
    @Size(max = 255)
    @Schema(example = "Password#123", requiredMode = RequiredMode.REQUIRED)
    private final String password;

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
