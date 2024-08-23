package com.recrutaibackend.institution.member;

import com.recrutaibackend.institution.InstitutionResponse;
import com.recrutaibackend.auth.user.UserResponse;

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
