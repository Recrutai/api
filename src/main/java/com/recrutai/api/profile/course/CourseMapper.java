package com.recrutai.api.profile.course;

import com.recrutai.api.auth.user.User;
import com.recrutai.api.institution.InstitutionMapper;
import com.recrutai.api.institution.school.School;
import com.recrutai.api.shared.DateUtils;
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
