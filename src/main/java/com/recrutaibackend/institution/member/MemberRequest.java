package com.recrutaibackend.institution.member;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record MemberRequest(
        @NotNull
        @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Long userId,

        @NotNull
        @Schema(example = "OWNER", requiredMode = Schema.RequiredMode.REQUIRED)
        MemberRole role,

        @NotNull
        @Schema(example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
        Long addedById
) {
}
