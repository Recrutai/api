package com.recrutaibackend.institution;

import com.recrutaibackend.address.AddressResponse;
import com.recrutaibackend.auth.user.UserResponse;

import java.time.Instant;

public record InstitutionResponse(
        long id,
        String name,
        String type,
        String headline,
        UserResponse owner,
        String industry,
        short companySizeId,
        int associatedEmployees,
        AddressResponse headquarters,
        String website,
        String about,
        Instant createdAt
) {
}
