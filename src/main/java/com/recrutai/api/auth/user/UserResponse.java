package com.recrutai.api.auth.user;

import com.recrutai.api.address.AddressResponse;
import com.recrutai.api.address.CityAddressRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponse(
        @Schema(example = "1")
        long id,

        @Schema(example = "John")
        String firstName,

        @Schema(example = "Smith")
        String lastName,

        @Schema(example = "Software Developer")
        String headline,

        @Schema(example = "john_smith@email.com")
        String email,

        @Schema(implementation = CityAddressRequest.class)
        AddressResponse location
) {
}
