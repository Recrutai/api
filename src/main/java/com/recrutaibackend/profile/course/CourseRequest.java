package com.recrutaibackend.profile.course;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.time.YearMonth;

public record CourseRequest(
        @NotNull
        Long schoolId,

        @NotEmpty
        @Size(max = 120)
        String name,

        @NotNull
        @Range(min = 1, max = 87600)
        Integer workloadHours,

        @NotNull
        YearMonth completionDate,

        @Size(max = 2000)
        String description
) {
}
