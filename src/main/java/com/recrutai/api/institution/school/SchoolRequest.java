package com.recrutai.api.institution.school;

import com.recrutai.api.institution.InstitutionRequest;
import com.recrutai.api.institution.InstitutionSize;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record SchoolRequest(
        @NotNull
        @Valid
        @Schema(implementation = InstitutionRequest.class, requiredMode = RequiredMode.REQUIRED)
        InstitutionRequest institution,

        @NotNull
        @Schema(example = "10.001+", requiredMode = RequiredMode.REQUIRED)
        InstitutionSize schoolSize
) {
}
