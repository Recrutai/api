package com.recrutai.api.profile.course;

import com.recrutai.api.institution.InstitutionSummaryResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.YearMonth;

@Getter
public class CourseResponse {
    @Schema(example = "1")
    private final long id;

    @Schema(implementation = InstitutionSummaryResponse.class)
    private final InstitutionSummaryResponse school;

    @Schema(example = "University of Oxford")
    private final String fallbackSchoolName;

    @Schema(example = "Back End Development Summer Course")
    private final String name;

    @Schema(example = "240")
    private final int workloadHours;

    @Schema(example = "2024-09")
    private final YearMonth completionDate;

    @Schema(example = "Course about programming and back end development focused on the development of APIs.")
    private final String description;

    public CourseResponse(
            long id,
            InstitutionSummaryResponse school,
            String fallbackSchoolName,
            String name,
            int workloadHours,
            YearMonth completionDate,
            String description
    ) {
        this.id = id;
        this.school = school;
        this.fallbackSchoolName = fallbackSchoolName;
        this.name = name;
        this.workloadHours = workloadHours;
        this.completionDate = completionDate;
        this.description = description;
    }

}
