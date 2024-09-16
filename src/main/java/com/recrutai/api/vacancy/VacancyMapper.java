package com.recrutai.api.vacancy;

import com.recrutai.api.address.AddressMapper;
import com.recrutai.api.auth.user.UserMapper;
import com.recrutai.api.institution.Institution;
import com.recrutai.api.institution.InstitutionMapper;
import com.recrutai.api.institution.member.Member;
import com.recrutai.api.shared.CurrencyCode;
import org.springframework.stereotype.Service;

@Service
public class VacancyMapper {
    private final AddressMapper addressMapper;
    private final InstitutionMapper institutionMapper;
    private final UserMapper userMapper;

    public VacancyMapper(AddressMapper addressMapper, InstitutionMapper institutionMapper, UserMapper userMapper) {
        this.addressMapper = addressMapper;
        this.institutionMapper = institutionMapper;
        this.userMapper = userMapper;
    }

    public Vacancy mapToEntity(VacancyRequest request, Institution institution, Member recruiter, Member publishedBy) {
        return new Vacancy(
                request.getTitle(),
                request.getDescription(),
                request.getEmploymentType(),
                request.getWorkModel(),
                addressMapper.mapToEntity(request.getLocation()),
                request.getSalary(),
                CurrencyCode.BRL,
                request.getPositions(),
                institution,
                recruiter,
                publishedBy
        );
    }

    public VacancyResponse mapToResponse(Vacancy entity) {
        return new VacancyResponse(
                entity.getId(),
                institutionMapper.mapToSummaryResponse(entity.getInstitution()),
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
