package com.recrutaibackend.vacancy;

import com.recrutaibackend.address.AddressResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

public record VacancyResponse(
        @Schema(example = "1")
        long id,

        @Schema(example = "Back End Developer")
        String title,

        @Schema(example = "We're looking for high skilled software engineers, with 3+ years of experience in back end development.")
        String description,

        @Schema(example = "FULL_TIME")
        String employmentType,

        @Schema(example = "ON_SITE")
        String workModel,

        @Schema(implementation = AddressResponse.class)
        AddressResponse location,

        @Schema(example = "5700")
        int salary,

        @Schema(example = "USD")
        String currencyCode,

        @Schema(example = "2")
        short positions,

        @Schema(example = "175")
        int applications,

        @Schema(example = "1")
        long recruiterId,

        @Schema(example = "1")
        long publishedById,

        Instant publishedAt,

        @Schema(example = "1")
        Long closedById,

        Instant closedAt
) {
}
