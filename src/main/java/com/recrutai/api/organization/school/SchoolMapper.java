package com.recrutai.api.organization.school;

import com.recrutai.api.organization.Organization;
import com.recrutai.api.organization.OrganizationMapper;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    private final OrganizationMapper organizationMapper;

    public SchoolMapper(OrganizationMapper organizationMapper) {
        this.organizationMapper = organizationMapper;
    }

    public School mapToEntity(SchoolRequest request, Organization organization) {
        return new School(organization, request.getSchoolSize());
    }

    public SchoolResponse mapToResponse(School entity) {
        if (entity == null) return null;
        return new SchoolResponse(
                organizationMapper.mapToResponse(entity.getOrganization()),
                entity.getSchoolSize().getValue(),
                entity.getAssociatedAlumni()
        );
    }

}
