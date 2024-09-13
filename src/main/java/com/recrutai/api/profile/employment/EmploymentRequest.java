package com.recrutai.api.profile.employment;

import com.recrutai.api.address.CityAddressRequest;
import com.recrutai.api.shared.EmploymentType;
import com.recrutai.api.shared.WorkModel;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.YearMonth;

public record EmploymentRequest(
        @Schema(example = "1", requiredMode = RequiredMode.NOT_REQUIRED)
        Long institutionId,

        @Schema(example = "University of Oxford", requiredMode = RequiredMode.NOT_REQUIRED)
        String fallbackInstitutionName,

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
        @PastOrPresent
        @Schema(example = "2022-03", requiredMode = RequiredMode.REQUIRED)
        YearMonth startDate,

        @PastOrPresent
        @Schema(example = "2024-09", requiredMode = RequiredMode.NOT_REQUIRED)
        YearMonth endDate
) {
}
