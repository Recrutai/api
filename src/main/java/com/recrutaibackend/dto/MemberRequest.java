package com.recrutaibackend.dto;

public record MemberRequest(
        Long userId,
        String role,
        Long addedById
) {
}
