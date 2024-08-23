package com.recrutaibackend.institution.school;

import com.recrutaibackend.institution.InstitutionRequest;
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
