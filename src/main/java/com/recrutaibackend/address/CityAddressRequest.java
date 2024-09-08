package com.recrutaibackend.address;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CityAddressRequest(
        @NotEmpty
        @Size(max = 80)
        @Schema(example = "Oxford", requiredMode = RequiredMode.REQUIRED)
        String city,

        @NotEmpty
        @Size(max = 80)
        @Schema(example = "Oxfordshire", requiredMode = RequiredMode.REQUIRED)
        String state,

        @NotEmpty
        @Size(max = 80)
        @Schema(example = "United Kingdom", requiredMode = RequiredMode.REQUIRED)
        String country
) {
}
