package com.recrutaibackend.profile.course;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.time.YearMonth;

public record CourseRequest(
        @NotNull
        @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
        Long schoolId,

        @NotEmpty
        @Size(max = 120)
        @Schema(example = "Back End Development Summer Course", requiredMode = RequiredMode.REQUIRED)
        String name,

        @NotNull
        @Range(min = 1, max = 87600)
        @Schema(example = "240", requiredMode = RequiredMode.REQUIRED)
        Integer workloadHours,

        @NotNull
        @PastOrPresent
        @Schema(example = "2024-09", requiredMode = RequiredMode.NOT_REQUIRED)
        YearMonth completionDate,

        @Size(max = 2000)
        @Schema(
                example = "Course about programming and back end development focused on the development of APIs.",
                requiredMode = RequiredMode.NOT_REQUIRED
        )
        String description
) {
}
