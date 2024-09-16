package com.recrutai.api.institution;

import com.recrutai.api.address.AddressMapper;
import com.recrutai.api.auth.user.User;
import com.recrutai.api.industry.Industry;
import org.springframework.stereotype.Service;

@Service
public class InstitutionMapper {
    private final AddressMapper addressMapper;

    public InstitutionMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public Institution mapToEntity(InstitutionRequest request, User founder, Industry industry) {
        return new Institution(
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

    public InstitutionResponse mapToResponse(Institution entity) {
        if (entity == null) return null;
        return new InstitutionResponse(
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

    public InstitutionSummaryResponse mapToSummaryResponse(Institution entity) {
        if (entity == null) return null;
        return new InstitutionSummaryResponse(
                entity.getId(),
                entity.getName(),
                entity.getHeadline(),
                entity.getIndustry().getName(),
                entity.getHeadquarters().getCity() + ", " + entity.getHeadquarters().getState()
        );
    }

}
