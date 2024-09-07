package com.recrutaibackend.auth.user;

import com.recrutaibackend.address.AddressResponse;

public record UserResponse(
        long id,
        String firstName,
        String lastName,
        String headline,
        String email,
        AddressResponse location
) {
}
