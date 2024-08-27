package com.recrutaibackend.institution;

import com.recrutaibackend.address.AddressMapper;
import com.recrutaibackend.auth.user.UserMapper;
import com.recrutaibackend.institution.industry.Industry;
import com.recrutaibackend.auth.user.User;
import org.springframework.stereotype.Service;

@Service
public class InstitutionMapper {

    private final UserMapper userMapper;

    public InstitutionMapper(UserMapper userMapper) {
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
                institution.getWebsite(),
                institution.getAbout(),
                institution.getCreatedAt()
        );
    }
}
