package com.recrutaibackend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public record VacancyRequest(
        @NotEmpty
        @Size(max = 120)
        String title,

        @NotEmpty
        @Size(max = 2500)
        String description,

        @NotEmpty
        @Size(max = 60)
        String workModel,

        @NotNull
        @Range(min = 1, max = 10_000_000)
        Double avgSalary,

        @NotNull
        @Range(min = 1, max = 500)
        Short positions,

        @NotNull
        Integer recruiterId,

        @NotNull
        Integer publisherId
) {
}
