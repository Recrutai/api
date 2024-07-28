package com.recrutaibackend.dto;

public record LoginRequest(
        String email,
        String password
) {
}
