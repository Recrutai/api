package com.recrutaibackend.profile.course;

import com.recrutaibackend.auth.user.User;
import com.recrutaibackend.institution.InstitutionMapper;
import com.recrutaibackend.institution.school.School;
import com.recrutaibackend.shared.DateUtils;
import org.springframework.stereotype.Service;

@Service
public class CourseMapper {
    private final InstitutionMapper institutionMapper;

    public CourseMapper(InstitutionMapper institutionMapper) {
        this.institutionMapper = institutionMapper;
    }

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
                institutionMapper.mapToSimpleResponse(entity.getSchool().getInstitution()),
                entity.getName(),
                entity.getWorkloadHours(),
                DateUtils.convertNumberToYearMonth(entity.getCompletionDate()),
                entity.getDescription()
        );
    }

}
