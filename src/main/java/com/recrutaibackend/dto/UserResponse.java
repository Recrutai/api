package com.recrutaibackend.dto;

public record UserResponse(
        long id,
        String firstName,
        String lastName,
        String headline,
        String email,
        AddressResponse location
) {
}
