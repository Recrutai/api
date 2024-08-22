package com.recrutaibackend.dto;

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
