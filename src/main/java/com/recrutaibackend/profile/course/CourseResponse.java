package com.recrutaibackend.profile.course;

import com.recrutaibackend.institution.school.SchoolResponse;
import com.recrutaibackend.auth.user.UserResponse;

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
