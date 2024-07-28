package com.recrutaibackend.dto;

public record AddressResponse(
        Integer id,
        String streetAddress,
        String city,
        String state,
        String country,
        String postalCode,
        Double latitude,
        Double longitude
) {
}
