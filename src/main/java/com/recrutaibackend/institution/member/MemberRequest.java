package com.recrutaibackend.institution.member;

import jakarta.validation.constraints.NotNull;

public record MemberRequest(
        @NotNull
        Long userId,

        @NotNull
        MemberRole role,

        @NotNull
        Long addedById
) {
}
