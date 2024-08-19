package com.recrutaibackend.dto;

public record AddressResponse(
        long id,
        String streetAddress,
        String city,
        String state,
        String country,
        String postalCode,
        Double latitude,
        Double longitude
) {
}
