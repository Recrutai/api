package com.recrutaibackend.institution;

import com.recrutaibackend.address.AddressRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record InstitutionRequest(
        @NotEmpty
        @Size(max = 120)
        String name,

        @NotNull
        InstitutionType type,

        @Size(max = 150)
        String headline,

        @NotNull
        Long ownerId,

        @NotEmpty
        @Size(max = 100)
        String industry,

        @NotNull
        Short companySizeId,

        @Size(max = 255)
        String website,

        @Size(max = 2500)
        String about
) {
}
