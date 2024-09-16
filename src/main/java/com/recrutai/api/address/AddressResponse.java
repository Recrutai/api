package com.recrutai.api.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class AddressResponse {
    @Schema(example = "1")
    private final long id;

    @Schema(example = "Wellington Square")
    private final String streetAddress;

    @Schema(example = "Oxford")
    private final String city;

    @Schema(example = "Oxfordshire")
    private final String state;

    @Schema(example = "United Kingdom")
    private final String country;

    @Schema(example = "OX1 2JD")
    private final String postalCode;

    @Schema(example = "51.757454")
    private final Double latitude;

    @Schema(example = "-1.254517")
    private final Double longitude;

    public AddressResponse(
            long id,
            String streetAddress,
            String city,
            String state,
            String country,
            String postalCode,
            Double latitude,
            Double longitude
    ) {
        this.id = id;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
