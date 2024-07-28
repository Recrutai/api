package com.recrutaibackend.dto;

public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
