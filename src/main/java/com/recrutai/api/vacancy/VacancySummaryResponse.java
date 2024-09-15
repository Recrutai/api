package com.recrutai.api.vacancy;

import com.recrutai.api.institution.InstitutionSummaryResponse;
import com.recrutai.api.shared.WorkModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;

@Getter
public class VacancySummaryResponse {
    @Schema(example = "1")
    private final long id;

    @Schema(implementation = InstitutionSummaryResponse.class)
    private final InstitutionSummaryResponse institution;

    @Schema(example = "Back End Developer")
    private final String title;

    @Schema(example = "Oxford, Oxfordshire")
    private final String location;

    @Schema(example = "ON_SITE")
    private final WorkModel workModel;

    private final Instant publishedAt;

    public VacancySummaryResponse(
            long id,
            String title,
            WorkModel workModel,
            Instant publishedAt,
            long institutionId,
            String institutionName,
            String location
    ) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.workModel = workModel;
        this.publishedAt = publishedAt;
        this.institution = new InstitutionSummaryResponse(institutionId, institutionName, null, null, null);
    }

}
