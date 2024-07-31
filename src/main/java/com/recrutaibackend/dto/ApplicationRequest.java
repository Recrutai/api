package com.recrutaibackend.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record ApplicationRequest(
        @NotNull
        Integer candidateId,

        @NotNull
        Integer vacancyId,

        @NotNull
        @Range(min = 1, max = 10_000_000)
        Double expectedSalary
) {
}
