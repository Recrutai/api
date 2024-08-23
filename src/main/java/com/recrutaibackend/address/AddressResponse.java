package com.recrutaibackend.address;

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
