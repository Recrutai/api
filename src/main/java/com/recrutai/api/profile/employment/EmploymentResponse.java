package com.recrutai.api.profile.employment;

import com.recrutai.api.institution.InstitutionSummaryResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.YearMonth;

@Getter
public class EmploymentResponse {
    @Schema(example = "1")
    private final long id;

    @Schema(implementation = InstitutionSummaryResponse.class)
    private final InstitutionSummaryResponse institution;

    @Schema(example = "University of Oxford")
    private final String fallbackInstitutionName;

    @Schema(example = "Back End Developer")
    private final String title;

    @Schema(example = "CONTRACT")
    private final String type;

    @Schema(example = "ON_SITE")
    private final String workModel;

    @Schema(example = "Oxford, Oxfordshire")
    private final String address;

    @Schema(example = "I've worked maintaining internal services developed in Java and Spring Boot.")
    private final String description;

    @Schema(example = "2022-03")
    private final YearMonth startDate;

    @Schema(example = "2024-09")
    private final YearMonth endDate;

    public EmploymentResponse(
            long id,
            InstitutionSummaryResponse institution,
            String fallbackInstitutionName,
            String title,
            String type,
            String workModel,
            String address,
            String description,
            YearMonth startDate,
            YearMonth endDate
    ) {
        this.id = id;
        this.institution = institution;
        this.fallbackInstitutionName = fallbackInstitutionName;
        this.title = title;
        this.type = type;
        this.workModel = workModel;
        this.address = address;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
