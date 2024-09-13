package com.recrutai.api.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CityAddressResponse {
    @Schema(example = "1")
    private final long id;

    @Schema(example = "Oxford")
    private final String city;

    @Schema(example = "Oxfordshire")
    private final String state;

    @Schema(example = "United Kingdom")
    private final String country;

    public CityAddressResponse(long id, String city, String state, String country) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.country = country;
    }

}
