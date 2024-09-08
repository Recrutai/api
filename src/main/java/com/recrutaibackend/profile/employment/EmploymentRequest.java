package com.recrutaibackend.profile.employment;

import com.recrutaibackend.address.CityAddressRequest;
import com.recrutaibackend.shared.WorkModel;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.YearMonth;

public record EmploymentRequest(
        @NotNull
        @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
        Long institutionId,

        @NotEmpty
        @Size(max = 100)
        @Schema(example = "Back End Developer", requiredMode = RequiredMode.REQUIRED)
        String title,

        @Schema(example = "CONTRACT", requiredMode = RequiredMode.NOT_REQUIRED)
        EmploymentType type,

        @Schema(example = "ON_SITE", requiredMode = RequiredMode.NOT_REQUIRED)
        WorkModel workModel,

        @Valid
        @Schema(implementation = CityAddressRequest.class, requiredMode = RequiredMode.NOT_REQUIRED)
        CityAddressRequest address,

        @Size(max = 2500)
        @Schema(
                example = "I've worked maintaining internal services developed in Java and Spring Boot.",
                requiredMode = RequiredMode.NOT_REQUIRED
        )
        String description,

        @NotNull
        @Schema(example = "2022-03", requiredMode = RequiredMode.REQUIRED)
        YearMonth startDate,

        @Schema(example = "2024-09", requiredMode = RequiredMode.NOT_REQUIRED)
        YearMonth endDate
) {
}
