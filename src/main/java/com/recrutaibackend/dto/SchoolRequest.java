package com.recrutaibackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record SchoolRequest(
        @NotNull
        Long id,

        @NotNull
        @Valid
        InstitutionRequest institution,

        @NotNull
        Integer schoolSizeId
) {
}
