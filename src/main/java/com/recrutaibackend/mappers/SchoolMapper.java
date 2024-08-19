package com.recrutaibackend.mappers;

import com.recrutaibackend.dto.SchoolResponse;
import com.recrutaibackend.model.Institution;
import com.recrutaibackend.model.InstitutionSize;
import com.recrutaibackend.model.School;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {

    private final InstitutionMapper institutionMapper;

    public SchoolMapper(InstitutionMapper institutionMapper) {
        this.institutionMapper = institutionMapper;
    }

    public School mapToEntity(Institution institution, InstitutionSize institutionSize) {
        return new School(institution, institutionSize);
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
