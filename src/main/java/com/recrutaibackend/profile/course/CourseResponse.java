package com.recrutaibackend.profile.course;

import java.time.YearMonth;

public record CourseResponse(
        long id,
        long schoolId,
        String name,
        int workloadHours,
        YearMonth completionDate,
        String description
) {
}
