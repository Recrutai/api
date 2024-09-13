package com.recrutai.api.profile.course;

import com.recrutai.api.institution.InstitutionSummaryResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.YearMonth;

public record CourseResponse(
        @Schema(example = "1")
        long id,

        @Schema(implementation = InstitutionSummaryResponse.class)
        InstitutionSummaryResponse school,

        @Schema(example = "Back End Development Summer Course")
        String name,

        @Schema(example = "240")
        int workloadHours,

        @Schema(example = "2024-09")
        YearMonth completionDate,

        @Schema(example = "Course about programming and back end development focused on the development of APIs.")
        String description
) {
}
