package com.recrutaibackend.vacancy;

import com.recrutaibackend.address.CityAddressRequest;
import com.recrutaibackend.shared.WorkModel;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public record VacancyRequest(
        @NotEmpty
        @Size(max = 100)
        @Schema(example = "Back End Developer", requiredMode = RequiredMode.REQUIRED)
        String title,

        @NotEmpty
        @Size(max = 3850)
        @Schema(
                example = "We're looking for high skilled software engineers, with 3+ years of experience in back end development.",
                requiredMode = RequiredMode.REQUIRED
        )
        String description,

        @NotNull
        @Schema(example = "ON_SITE", requiredMode = RequiredMode.REQUIRED)
        WorkModel workModel,

        @Valid
        @Schema(implementation = CityAddressRequest.class, requiredMode = RequiredMode.NOT_REQUIRED)
        CityAddressRequest location,

        @NotNull
        @Range(min = 1, max = 1_000_000)
        @Schema(example = "5700", requiredMode = RequiredMode.REQUIRED)
        Integer salary,

        @NotNull
        @Range(min = 1, max = 500)
        @Schema(example = "2", requiredMode = RequiredMode.REQUIRED)
        Short positions,

        @NotNull
        @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
        Long recruiterId,

        @NotNull
        @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
        Long publishedById
) {
}
