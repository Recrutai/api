package com.recrutai.api.vacancy;

import com.recrutai.api.address.AddressMapper;
import com.recrutai.api.auth.user.UserMapper;
import com.recrutai.api.organization.Organization;
import com.recrutai.api.organization.OrganizationMapper;
import com.recrutai.api.organization.member.Member;
import com.recrutai.api.shared.CurrencyCode;
import org.springframework.stereotype.Service;

@Service
public class VacancyMapper {
    private final AddressMapper addressMapper;
    private final OrganizationMapper organizationMapper;
    private final UserMapper userMapper;

    public VacancyMapper(AddressMapper addressMapper, OrganizationMapper organizationMapper, UserMapper userMapper) {
        this.addressMapper = addressMapper;
        this.organizationMapper = organizationMapper;
        this.userMapper = userMapper;
    }

    public Vacancy mapToEntity(VacancyRequest request, Organization organization, Member recruiter, Member publishedBy) {
        return new Vacancy(
                request.getTitle(),
                request.getDescription(),
                request.getEmploymentType(),
                request.getWorkModel(),
                addressMapper.mapToEntity(request.getLocation()),
                request.getSalary(),
                CurrencyCode.BRL,
                request.getPositions(),
                organization,
                recruiter,
                publishedBy
        );
    }

    public VacancyResponse mapToResponse(Vacancy entity) {
        return new VacancyResponse(
                entity.getId(),
                organizationMapper.mapToSummaryResponse(entity.getOrganization()),
                entity.getTitle(),
                entity.getDescription(),
                entity.getEmploymentType().toString(),
                entity.getWorkModel().toString(),
                addressMapper.mapToCityResponse(entity.getLocation()),
                entity.getSalary(),
                entity.getCurrencyCode().toString(),
                entity.getPositions(),
                entity.getApplications(),
                userMapper.mapToResponse(entity.getRecruiter().getUser()),
                userMapper.mapToResponse(entity.getPublishedBy().getUser()),
                entity.getPublishedAt(),
                entity.getClosedBy() != null ? userMapper.mapToResponse(entity.getClosedBy().getUser()) : null,
                entity.getClosedAt()
        );
    }

}
