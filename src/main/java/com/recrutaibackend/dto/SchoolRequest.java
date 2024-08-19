package com.recrutaibackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record SchoolRequest(
        @NotNull
        @Valid
        InstitutionRequest institution,

        @NotNull
        Short schoolSizeId
) {
}
