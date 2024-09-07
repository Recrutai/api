package com.recrutaibackend.institution;

import com.recrutaibackend.address.AddressMapper;
import com.recrutaibackend.auth.user.User;
import com.recrutaibackend.industry.Industry;
import org.springframework.stereotype.Service;

@Service
public class InstitutionMapper {
    private final AddressMapper addressMapper;

    public InstitutionMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public Institution mapToEntity(InstitutionRequest request, User founder, Industry industry) {
        return new Institution(
                request.name(),
                request.type(),
                request.headline(),
                founder,
                industry,
                request.companySize(),
                addressMapper.mapToEntity(request.headquarters()),
                request.website(),
                request.about()
        );
    }

    public InstitutionResponse mapToResponse(Institution entity) {
        return new InstitutionResponse(
                entity.getId(),
                entity.getName(),
                entity.getType().toString(),
                entity.getHeadline(),
                entity.getFounder().getId(),
                entity.getIndustry().getName(),
                entity.getCompanySize().getValue(),
                entity.getAssociatedEmployees(),
                addressMapper.mapToResponse(entity.getHeadquarters()),
                entity.getWebsite(),
                entity.getAbout(),
                entity.getCreatedAt()
        );
    }

}
