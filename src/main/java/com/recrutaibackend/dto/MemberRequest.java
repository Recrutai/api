package com.recrutaibackend.dto;

public record MemberRequest(
        Long userId,
        Long institutionId,
        String role,
        Long addedById
) {
}
