package com.recrutaibackend.mappers;

import com.recrutaibackend.dto.InstitutionRequest;
import com.recrutaibackend.dto.InstitutionResponse;
import com.recrutaibackend.model.Industry;
import com.recrutaibackend.model.Institution;
import com.recrutaibackend.model.InstitutionSize;
import com.recrutaibackend.model.User;
import org.springframework.stereotype.Service;

@Service
public class InstitutionMapper {

    private final AddressMapper addressMapper;
    private final UserMapper userMapper;

    public InstitutionMapper(AddressMapper addressMapper, UserMapper userMapper) {
        this.addressMapper = addressMapper;
        this.userMapper = userMapper;
    }

    public Institution mapToEntity(InstitutionRequest request, User owner, Industry industry, InstitutionSize size) {
        return new Institution(
                request.name(),
                request.type(),
                request.headline(),
                owner,
                industry,
                size,
                addressMapper.mapToEntity(request.headquarters()),
                request.website(),
                request.about()
        );
    }

    public InstitutionResponse mapToResponse(Institution institution) {
        return new InstitutionResponse(
                institution.getId(),
                institution.getName(),
                institution.getType().toString(),
                institution.getHeadline(),
                userMapper.mapToResponse(institution.getOwner()),
                institution.getIndustry().getName(),
                institution.getCompanySize().getId(),
                institution.getAssociatedEmployees(),
                addressMapper.mapToResponse(institution.getHeadquarters()),
                institution.getWebsite(),
                institution.getAbout(),
                institution.getCreatedAt()
        );
    }
}
