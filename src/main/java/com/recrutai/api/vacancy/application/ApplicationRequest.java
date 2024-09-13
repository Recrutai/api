package com.recrutai.api.vacancy.application;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record ApplicationRequest(
        @NotNull
        @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
        Long candidateId,

        @NotNull
        @Range(min = 1, max = 1_000_000)
        @Schema(example = "5400", requiredMode = RequiredMode.REQUIRED)
        Integer expectedSalary
) {
}
