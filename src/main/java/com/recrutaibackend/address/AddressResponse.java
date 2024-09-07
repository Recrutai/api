package com.recrutaibackend.address;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
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
