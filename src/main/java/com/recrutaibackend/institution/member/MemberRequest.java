package com.recrutaibackend.institution.member;

public record MemberRequest(
        Long userId,
        String role,
        Long addedById
) {
}
