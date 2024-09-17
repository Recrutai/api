package com.recrutai.api.vacancy;

import com.recrutai.api.address.CityAddressRequest;
import com.recrutai.api.shared.EmploymentType;
import com.recrutai.api.shared.WorkModel;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class VacancyRequest {
    @NotEmpty
    @Size(max = 100)
    @Schema(example = "Back End Developer", requiredMode = RequiredMode.REQUIRED)
    private final String title;

    @NotNull
    @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
    private final Long organizationId;

    @NotEmpty
    @Size(max = 3850)
    @Schema(
            example = "We're looking for high skilled software engineers, with 3+ years of experience in back end development.",
            requiredMode = RequiredMode.REQUIRED
    )
    private final String description;

    @NotNull
    @Schema(example = "FULL_TIME", requiredMode = RequiredMode.REQUIRED)
    private final EmploymentType employmentType;

    @NotNull
    @Schema(example = "ON_SITE", requiredMode = RequiredMode.REQUIRED)
    private final WorkModel workModel;

    @Valid
    @Schema(implementation = CityAddressRequest.class, requiredMode = RequiredMode.NOT_REQUIRED)
    private final CityAddressRequest location;

    @NotNull
    @Range(min = 1, max = 1_000_000)
    @Schema(example = "5700", requiredMode = RequiredMode.REQUIRED)
    private final Integer salary;

    @NotNull
    @Range(min = 1, max = 500)
    @Schema(example = "2", requiredMode = RequiredMode.REQUIRED)
    private final Short positions;

    @NotNull
    @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
    private final Long recruiterId;

    @NotNull
    @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
    private final Long publishedById;

    public VacancyRequest(
            String title,
            Long organizationId,
            String description,
            EmploymentType employmentType,
            WorkModel workModel,
            CityAddressRequest location,
            Integer salary,
            Short positions,
            Long recruiterId,
            Long publishedById) {
        this.title = title;
        this.organizationId = organizationId;
        this.description = description;
        this.employmentType = employmentType;
        this.workModel = workModel;
        this.location = location;
        this.salary = salary;
        this.positions = positions;
        this.recruiterId = recruiterId;
        this.publishedById = publishedById;
    }

}
