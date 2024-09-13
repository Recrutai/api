package com.recrutai.api.address;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CityAddressRequest {
    @NotEmpty
    @Size(max = 80)
    @Schema(example = "Oxford", requiredMode = RequiredMode.REQUIRED)
    private final String city;

    @NotEmpty
    @Size(max = 80)
    @Schema(example = "Oxfordshire", requiredMode = RequiredMode.REQUIRED)
    private final String state;

    @NotEmpty
    @Size(max = 80)
    @Schema(example = "United Kingdom", requiredMode = RequiredMode.REQUIRED)
    private final String country;

    public CityAddressRequest(String city, String state, String country) {
        this.city = city;
        this.state = state;
        this.country = country;
    }

}
