package com.recrutai.api.organization;

import com.recrutai.api.address.AddressMapper;
import com.recrutai.api.auth.user.User;
import com.recrutai.api.industry.Industry;
import org.springframework.stereotype.Service;

@Service
public class OrganizationMapper {
    private final AddressMapper addressMapper;

    public OrganizationMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public Organization mapToEntity(OrganizationRequest request, User founder, Industry industry) {
        return new Organization(
                request.getName(),
                request.getType(),
                request.getHeadline(),
                founder,
                industry,
                request.getCompanySize(),
                addressMapper.mapToEntity(request.getHeadquarters()),
                request.getWebsite(),
                request.getAbout()
        );
    }

    public OrganizationResponse mapToResponse(Organization entity) {
        if (entity == null) return null;
        return new OrganizationResponse(
                entity.getId(),
                entity.getName(),
                entity.getType().toString(),
                entity.getHeadline(),
                entity.getIndustry().getName(),
                entity.getCompanySize().getValue(),
                entity.getAssociatedEmployees(),
                addressMapper.mapToResponse(entity.getHeadquarters()),
                entity.getWebsite(),
                entity.getAbout(),
                entity.getCreatedAt()
        );
    }

    public OrganizationSummaryResponse mapToSummaryResponse(Organization entity) {
        if (entity == null) return null;
        var headquarters = entity.getHeadquarters();
        return new OrganizationSummaryResponse(
                entity.getId(),
                entity.getName(),
                entity.getHeadline(),
                entity.getIndustry().getName(),
                headquarters != null ? headquarters.getCity() + ", " + headquarters.getState() : null
        );
    }

}
