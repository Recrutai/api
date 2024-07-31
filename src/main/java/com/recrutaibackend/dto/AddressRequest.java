package com.recrutaibackend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public record AddressRequest(
        @Size(max = 70)
        String streetAddress,

        @NotEmpty
        @Size(max = 70)
        String city,

        @NotEmpty
        @Size(max = 70)
        String state,

        @NotEmpty
        @Size(max = 70)
        String country,

        @Size(max = 20)
        String postalCode,

        @Range(min = -90, max = 90)
        Double latitude,

        @Range(min = -180, max = 180)
        Double longitude
) {
}
