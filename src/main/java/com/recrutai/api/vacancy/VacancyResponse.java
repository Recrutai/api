package com.recrutai.api.vacancy;

import com.recrutai.api.address.AddressResponse;
import com.recrutai.api.institution.InstitutionSummaryResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;

@Getter
public class VacancyResponse {
    @Schema(example = "1")
    private final long id;

    @Schema(implementation = InstitutionSummaryResponse.class)
    private final InstitutionSummaryResponse institution;

    @Schema(example = "Back End Developer")
    private final String title;

    @Schema(example = "We're looking for high skilled software engineers, with 3+ years of experience in back end development.")
    private final String description;

    @Schema(example = "FULL_TIME")
    private final String employmentType;

    @Schema(example = "ON_SITE")
    private final String workModel;

    @Schema(implementation = AddressResponse.class)
    private final AddressResponse location;

    @Schema(example = "5700")
    private final int salary;

    @Schema(example = "USD")
    private final String currencyCode;

    @Schema(example = "2")
    private final short positions;

    @Schema(example = "175")
    private final int applications;

    @Schema(example = "1")
    private final long recruiterId;

    @Schema(example = "1")
    private final long publishedById;

    private final Instant publishedAt;

    @Schema(example = "1")
    private final Long closedById;

    private final Instant closedAt;

    public VacancyResponse(
            long id,
            InstitutionSummaryResponse institution,
            String title,
            String description,
            String employmentType,
            String workModel,
            AddressResponse location,
            int salary,
            String currencyCode,
            short positions,
            int applications,
            long recruiterId,
            long publishedById,
            Instant publishedAt,
            Long closedById,
            Instant closedAt
    ) {
        this.id = id;
        this.institution = institution;
        this.title = title;
        this.description = description;
        this.employmentType = employmentType;
        this.workModel = workModel;
        this.location = location;
        this.salary = salary;
        this.currencyCode = currencyCode;
        this.positions = positions;
        this.applications = applications;
        this.recruiterId = recruiterId;
        this.publishedById = publishedById;
        this.publishedAt = publishedAt;
        this.closedById = closedById;
        this.closedAt = closedAt;
    }

}
