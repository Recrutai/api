package com.recrutaibackend.dto;

public record UserResponse(
        int id,
        String firstName,
        String lastName,
        String email
) {
}
