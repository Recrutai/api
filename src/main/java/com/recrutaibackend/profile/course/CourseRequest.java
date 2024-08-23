package com.recrutaibackend.profile.course;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

public record CourseRequest(
        @NotNull
        Long userId,

        @NotNull
        Long schoolId,

        @NotEmpty
        @Size(max = 120)
        String name,

        @NotNull
        @Range(min = 1, max = 87600)
        Integer workloadHours,

        @NotNull
        LocalDate completionDate,

        @Size(max = 2000)
        String description
) {
}
