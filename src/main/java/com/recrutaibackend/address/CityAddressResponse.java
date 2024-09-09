package com.recrutaibackend.address;

import io.swagger.v3.oas.annotations.media.Schema;

public record CityAddressResponse(
        @Schema(example = "1")
        long id,

        @Schema(example = "Oxford")
        String city,

        @Schema(example = "Oxfordshire")
        String state,

        @Schema(example = "United Kingdom")
        String country
) {
}
