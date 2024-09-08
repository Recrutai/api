package com.recrutaibackend.institution.member;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

public record MemberResponse(
        @Schema(example = "1")
        long id,

        @Schema(example = "1")
        long userId,

        @Schema(example = "1")
        long institutionId,

        @Schema(example = "OWNER")
        String role,

        @Schema(example = "2")
        Long addedById,

        Instant addedAt,

        @Schema(example = "2")
        Long removedById,

        Instant removedAt
) {
}
