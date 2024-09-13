package com.recrutai.api.profile.course;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import java.time.YearMonth;

@Getter
public class CourseRequest {
    @Schema(example = "1", requiredMode = RequiredMode.NOT_REQUIRED)
    private final Long schoolId;

    @Schema(example = "University of Oxford", requiredMode = RequiredMode.NOT_REQUIRED)
    private final String fallbackSchoolName;

    @NotEmpty
    @Size(max = 120)
    @Schema(example = "Back End Development Summer Course", requiredMode = RequiredMode.REQUIRED)
    private final String name;

    @NotNull
    @Range(min = 1, max = 87600)
    @Schema(example = "240", requiredMode = RequiredMode.REQUIRED)
    private final Integer workloadHours;

    @NotNull
    @PastOrPresent
    @Schema(example = "2024-09", requiredMode = RequiredMode.NOT_REQUIRED)
    private final YearMonth completionDate;

    @Size(max = 2000)
    @Schema(
            example = "Course about programming and back end development focused on the development of APIs.",
            requiredMode = RequiredMode.NOT_REQUIRED
    )
    private final String description;

    public CourseRequest(
            Long schoolId,
            String fallbackSchoolName,
            String name,
            Integer workloadHours,
            YearMonth completionDate,
            String description
    ) {
        this.schoolId = schoolId;
        this.fallbackSchoolName = fallbackSchoolName;
        this.name = name;
        this.workloadHours = workloadHours;
        this.completionDate = completionDate;
        this.description = description;
    }

}
