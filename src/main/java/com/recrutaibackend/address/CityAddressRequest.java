package com.recrutaibackend.address;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CityAddressRequest(
        @NotEmpty
        @Size(max = 80)
        String city,

        @NotEmpty
        @Size(max = 80)
        String state,

        @NotEmpty
        @Size(max = 80)
        String country
) {
}
