package com.recrutaibackend.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record AddressResponse(
        @Schema(example = "1")
        long id,

        @Schema(example = "Wellington Square")
        String streetAddress,

        @Schema(example = "Oxford")
        String city,

        @Schema(example = "Oxfordshire")
        String state,

        @Schema(example = "United Kingdom")
        String country,

        @Schema(example = "OX1 2JD")
        String postalCode,

        @Schema(example = "51.757454")
        Double latitude,

        @Schema(example = "-1.254517")
        Double longitude
) {
}
