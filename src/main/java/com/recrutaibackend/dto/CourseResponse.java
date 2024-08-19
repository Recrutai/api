package com.recrutaibackend.dto;

import java.time.LocalDate;

public record CourseResponse(
        long id,
        UserResponse user,
        SchoolResponse school,
        String name,
        int workloadHours,
        LocalDate completionDate,
        String description
) {
}
