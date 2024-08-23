package com.recrutaibackend.institution.school;

import com.recrutaibackend.institution.Institution;
import com.recrutaibackend.institution.InstitutionMapper;
import com.recrutaibackend.institution.InstitutionSize;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {

    private final InstitutionMapper institutionMapper;

    public SchoolMapper(InstitutionMapper institutionMapper) {
        this.institutionMapper = institutionMapper;
    }

    public School mapToEntity(Institution institution, InstitutionSize schoolSize) {
        return new School(institution, schoolSize);
    }

    public SchoolResponse mapToResponse(School school) {
        return new SchoolResponse(
                school.getId(),
                institutionMapper.mapToResponse(school.getInstitution()),
                school.getSchoolSize().getId(),
                school.getAssociatedAlumni()
        );
    }

}
