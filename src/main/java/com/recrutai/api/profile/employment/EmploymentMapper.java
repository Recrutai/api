package com.recrutai.api.profile.employment;

import com.recrutai.api.address.AddressMapper;
import com.recrutai.api.auth.user.User;
import com.recrutai.api.organization.Organization;
import com.recrutai.api.organization.OrganizationMapper;
import com.recrutai.api.shared.DateUtils;
import org.springframework.stereotype.Service;

@Service
public class EmploymentMapper {
    private final AddressMapper addressMapper;
    private final OrganizationMapper organizationMapper;

    public EmploymentMapper(AddressMapper addressMapper, OrganizationMapper organizationMapper) {
        this.addressMapper = addressMapper;
        this.organizationMapper = organizationMapper;
    }

    public Employment mapToEntity(EmploymentRequest request, User user, Organization organization) {
        return new Employment(
                user,
                organization,
                request.getFallbackOrganizationName(),
                request.getTitle(),
                request.getType(),
                request.getWorkModel(),
                addressMapper.mapToEntity(request.getAddress()),
                request.getDescription(),
                DateUtils.convertYearMonthToNumber(request.getStartDate()),
                request.getEndDate() != null ? DateUtils.convertYearMonthToNumber(request.getEndDate()) : null
        );
    }

    public EmploymentResponse mapToResponse(Employment entity) {
        return new EmploymentResponse(
                entity.getId(),
                organizationMapper.mapToSummaryResponse(entity.getOrganization()),
                entity.getFallbackOrganizationName(),
                entity.getTitle(),
                entity.getType().toString(),
                entity.getWorkModel().toString(),
                entity.getAddress().getCity() + ", " + entity.getAddress().getState(),
                entity.getDescription(),
                DateUtils.convertNumberToYearMonth(entity.getStartDate()),
                entity.getEndDate() != null ? DateUtils.convertNumberToYearMonth(entity.getEndDate()) : null
        );
    }

}
