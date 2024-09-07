package com.recrutaibackend.profile.course;

import com.recrutaibackend.auth.user.User;
import com.recrutaibackend.institution.school.School;
import com.recrutaibackend.shared.DateUtils;
import org.springframework.stereotype.Service;

@Service
public class CourseMapper {

    public Course mapToEntity(CourseRequest request, User user, School school) {
        return new Course(
                user,
                school,
                request.name(),
                request.workloadHours(),
                DateUtils.convertYearMonthToNumber(request.completionDate()),
                request.description()
        );
    }

    public CourseResponse mapToResponse(Course entity) {
        return new CourseResponse(
                entity.getId(),
                entity.getSchool().getId(),
                entity.getName(),
                entity.getWorkloadHours(),
                DateUtils.convertNumberToYearMonth(entity.getCompletionDate()),
                entity.getDescription()
        );
    }

}
