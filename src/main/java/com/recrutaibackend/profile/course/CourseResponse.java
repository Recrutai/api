package com.recrutaibackend.profile.course;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.YearMonth;

public record CourseResponse(
        @Schema(example = "1")
        long id,

        @Schema(example = "1")
        long schoolId,

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
