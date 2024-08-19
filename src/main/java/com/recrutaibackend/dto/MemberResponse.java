package com.recrutaibackend.dto;

import java.time.Instant;

public record MemberResponse(
        long id,
        UserResponse user,
        InstitutionResponse institution,
        String role,
        long addedById,
        Instant addedAt,
        long removedById,
        Instant removedAt
) {
}
