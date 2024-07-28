package com.recrutaibackend.dto;

public record AddressRequest(
        String streetAddress,
        String city,
        String state,
        String country,
        String postalCode,
        Double latitude,
        Double longitude
) {
}
