package com.recrutai.api.address;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class AddressRequest {
    @Size(max = 70)
    @Schema(example = "Wellington Square", requiredMode = RequiredMode.NOT_REQUIRED)
    private final String streetAddress;

    @NotEmpty
    @Size(max = 70)
    @Schema(example = "Oxford", requiredMode = RequiredMode.REQUIRED)
    private final String city;

    @NotEmpty
    @Size(max = 70)
    @Schema(example = "Oxfordshire", requiredMode = RequiredMode.REQUIRED)
    private final String state;

    @NotEmpty
    @Size(max = 70)
    @Schema(example = "United Kingdom", requiredMode = RequiredMode.REQUIRED)
    private final String country;

    @Size(max = 20)
    @Schema(example = "OX1 2JD", requiredMode = RequiredMode.NOT_REQUIRED)
    private final String postalCode;

    @Range(min = -90, max = 90)
    @Schema(example = "51.757454", requiredMode = RequiredMode.NOT_REQUIRED)
    private final Double latitude;

    @Range(min = -180, max = 180)
    @Schema(example = "-1.254517", requiredMode = RequiredMode.NOT_REQUIRED)
    private final Double longitude;

    public AddressRequest(
            String streetAddress,
            String city,
            String state,
            String country,
            String postalCode,
            Double latitude,
            Double longitude
    ) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
