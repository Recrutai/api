package com.recrutaibackend.vacancy.application;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

public record ApplicationResponse(
        @Schema(example = "1")
        long id,

        @Schema(example = "1")
        long userId,

        @Schema(example = "5400")
        int expectedSalary,

        @Schema(example = "USD")
        String currencyCode,

        Instant appliedAt
) {
}
