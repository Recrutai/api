package com.recrutaibackend.profile.employment;

import com.recrutaibackend.address.AddressResponse;
import com.recrutaibackend.institution.InstitutionSummaryResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.YearMonth;

public record EmploymentResponse(
        @Schema(example = "1")
        long id,

        @Schema(implementation = InstitutionSummaryResponse.class)
        InstitutionSummaryResponse institution,

        @Schema(example = "Back End Developer")
        String title,

        @Schema(example = "CONTRACT")
        String type,

        @Schema(example = "ON_SITE")
        String workModel,

        @Schema(implementation = AddressResponse.class)
        AddressResponse address,

        @Schema(example = "I've worked maintaining internal services developed in Java and Spring Boot.")
        String description,

        @Schema(example = "2022-03")
        YearMonth startDate,

        @Schema(example = "2024-09")
        YearMonth endDate
) {
}
