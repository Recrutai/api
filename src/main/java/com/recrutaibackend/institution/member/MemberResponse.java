package com.recrutaibackend.institution.member;

import java.time.Instant;

public record MemberResponse(
        long id,
        long userId,
        long institutionId,
        String role,
        Long addedById,
        Instant addedAt,
        Long removedById,
        Instant removedAt
) {
}
