package com.recrutaibackend.vacancy.application;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record ApplicationRequest(
        @NotNull
        Long candidateId,

        @NotNull
        Long vacancyId,

        @NotNull
        @Range(min = 1, max = 1_000_000)
        Integer expectedSalary
) {
}
