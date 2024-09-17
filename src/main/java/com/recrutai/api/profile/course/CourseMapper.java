package com.recrutai.api.profile.course;

import com.recrutai.api.auth.user.User;
import com.recrutai.api.organization.OrganizationMapper;
import com.recrutai.api.organization.school.School;
import com.recrutai.api.shared.DateUtils;
import org.springframework.stereotype.Service;

@Service
public class CourseMapper {
    private final OrganizationMapper organizationMapper;

    public CourseMapper(OrganizationMapper organizationMapper) {
        this.organizationMapper = organizationMapper;
    }

    public Course mapToEntity(CourseRequest request, User user, School school) {
        return new Course(
                user,
                school,
                request.getFallbackSchoolName(),
                request.getName(),
                request.getWorkloadHours(),
                DateUtils.convertYearMonthToNumber(request.getCompletionDate()),
                request.getDescription()
        );
    }

    public CourseResponse mapToResponse(Course entity) {
        var organization = entity.getSchool() != null ? entity.getSchool().getOrganization() : null;
        return new CourseResponse(
                entity.getId(),
                organizationMapper.mapToSummaryResponse(organization),
                entity.getFallbackSchoolName(),
                entity.getName(),
                entity.getWorkloadHours(),
                DateUtils.convertNumberToYearMonth(entity.getCompletionDate()),
                entity.getDescription()
        );
    }

}
