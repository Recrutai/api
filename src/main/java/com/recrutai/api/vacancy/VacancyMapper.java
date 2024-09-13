package com.recrutai.api.vacancy;

import com.recrutai.api.address.AddressMapper;
import com.recrutai.api.institution.Institution;
import com.recrutai.api.institution.InstitutionMapper;
import com.recrutai.api.institution.member.Member;
import com.recrutai.api.shared.CurrencyCode;
import org.springframework.stereotype.Service;

@Service
public class VacancyMapper {
    private final AddressMapper addressMapper;
    private final InstitutionMapper institutionMapper;

    public VacancyMapper(AddressMapper addressMapper, InstitutionMapper institutionMapper) {
        this.addressMapper = addressMapper;
        this.institutionMapper = institutionMapper;
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
                addressMapper.mapToResponse(entity.getLocation()),
                entity.getSalary(),
                entity.getCurrencyCode().toString(),
                entity.getPositions(),
                entity.getApplications(),
                entity.getRecruiter().getId(),
                entity.getPublishedBy().getId(),
                entity.getPublishedAt(),
                entity.getClosedBy() != null ? entity.getClosedBy().getId() : null,
                entity.getClosedAt()
        );
    }

}
