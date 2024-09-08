package com.recrutaibackend.address;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public record AddressRequest(
        @Size(max = 70)
        @Schema(example = "Wellington Square", requiredMode = RequiredMode.NOT_REQUIRED)
        String streetAddress,

        @NotEmpty
        @Size(max = 70)
        @Schema(example = "Oxford", requiredMode = RequiredMode.REQUIRED)
        String city,

        @NotEmpty
        @Size(max = 70)
        @Schema(example = "Oxfordshire", requiredMode = RequiredMode.REQUIRED)
        String state,

        @NotEmpty
        @Size(max = 70)
        @Schema(example = "United Kingdom", requiredMode = RequiredMode.REQUIRED)
        String country,

        @Size(max = 20)
        @Schema(example = "OX1 2JD", requiredMode = RequiredMode.NOT_REQUIRED)
        String postalCode,

        @Range(min = -90, max = 90)
        @Schema(example = "51.757454", requiredMode = RequiredMode.NOT_REQUIRED)
        Double latitude,

        @Range(min = -180, max = 180)
        @Schema(example = "-1.254517", requiredMode = RequiredMode.NOT_REQUIRED)
        Double longitude
) {
}
