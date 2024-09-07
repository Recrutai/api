package com.recrutaibackend.institution;

import com.recrutaibackend.address.AddressResponse;

import java.time.Instant;

public record InstitutionResponse(
        long id,
        String name,
        String type,
        String headline,
        long founderId,
        String industry,
        String companySize,
        int associatedEmployees,
        AddressResponse headquarters,
        String website,
        String about,
        Instant createdAt
) {
}
