package com.recrutaibackend.institution.school;

import com.recrutaibackend.institution.Institution;
import com.recrutaibackend.institution.InstitutionMapper;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    private final InstitutionMapper institutionMapper;

    public SchoolMapper(InstitutionMapper institutionMapper) {
        this.institutionMapper = institutionMapper;
    }

    public School mapToEntity(SchoolRequest request, Institution institution) {
        return new School(institution, request.schoolSize());
    }

    public SchoolResponse mapToResponse(School entity) {
        return new SchoolResponse(
                entity.getId(),
                institutionMapper.mapToResponse(entity.getInstitution()),
                entity.getSchoolSize().getValue(),
                entity.getAssociatedAlumni()
        );
    }

}
