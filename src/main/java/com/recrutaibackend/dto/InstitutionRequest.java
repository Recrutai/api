package com.recrutaibackend.dto;

import com.recrutaibackend.model.InstitutionType;
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
        Integer companySizeId,

        @Valid
        AddressRequest headquarters,

        @Size(max = 255)
        String website,

        @Size(max = 2500)
        String about
) {
}
