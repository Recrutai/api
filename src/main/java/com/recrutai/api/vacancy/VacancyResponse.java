package com.recrutai.api.vacancy;

import com.recrutai.api.address.CityAddressResponse;
import com.recrutai.api.auth.user.UserResponse;
import com.recrutai.api.organization.OrganizationSummaryResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;

@Getter
public class VacancyResponse {
    @Schema(example = "1")
    private final long id;

    @Schema(implementation = OrganizationSummaryResponse.class)
    private final OrganizationSummaryResponse organization;

    @Schema(example = "Back End Developer")
    private final String title;

    @Schema(example = "We're looking for high skilled software engineers, with 3+ years of experience in back end development.")
    private final String description;

    @Schema(example = "FULL_TIME")
    private final String employmentType;

    @Schema(example = "ON_SITE")
    private final String workModel;

    @Schema(implementation = CityAddressResponse.class)
    private final CityAddressResponse location;

    @Schema(example = "5700")
    private final int salary;

    @Schema(example = "USD")
    private final String currencyCode;

    @Schema(example = "2")
    private final short positions;

    @Schema(example = "175")
    private final int applications;

    @Schema(implementation = UserResponse.class)
    private final UserResponse recruiter;

    @Schema(implementation = UserResponse.class)
    private final UserResponse publishedBy;

    private final Instant publishedAt;

    @Schema(implementation = UserResponse.class)
    private final UserResponse closedBy;

    private final Instant closedAt;

    public VacancyResponse(
            long id,
            OrganizationSummaryResponse organization,
            String title,
            String description,
            String employmentType,
            String workModel,
            CityAddressResponse location,
            int salary,
            String currencyCode,
            short positions,
            int applications,
            UserResponse recruiter,
            UserResponse publishedBy,
            Instant publishedAt,
            UserResponse closedBy,
            Instant closedAt
    ) {
        this.id = id;
        this.organization = organization;
        this.title = title;
        this.description = description;
        this.employmentType = employmentType;
        this.workModel = workModel;
        this.location = location;
        this.salary = salary;
        this.currencyCode = currencyCode;
        this.positions = positions;
        this.applications = applications;
        this.recruiter = recruiter;
        this.publishedBy = publishedBy;
        this.publishedAt = publishedAt;
        this.closedBy = closedBy;
        this.closedAt = closedAt;
    }

}
