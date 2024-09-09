package com.recrutaibackend.vacancy;

import com.recrutaibackend.address.AddressMapper;
import com.recrutaibackend.institution.InstitutionMapper;
import com.recrutaibackend.institution.member.Member;
import com.recrutaibackend.shared.CurrencyCode;
import org.springframework.stereotype.Service;

@Service
public class VacancyMapper {
    private final AddressMapper addressMapper;
    private final InstitutionMapper institutionMapper;

    public VacancyMapper(AddressMapper addressMapper, InstitutionMapper institutionMapper) {
        this.addressMapper = addressMapper;
        this.institutionMapper = institutionMapper;
    }

    public Vacancy mapToEntity(VacancyRequest request, Member recruiter, Member publishedBy) {
        return new Vacancy(
                request.title(),
                request.description(),
                request.employmentType(),
                request.workModel(),
                addressMapper.mapToEntity(request.location()),
                request.salary(),
                CurrencyCode.BRL,
                request.positions(),
                recruiter,
                publishedBy
        );
    }

    public VacancyResponse mapToResponse(Vacancy entity) {
        return new VacancyResponse(
                entity.getId(),
                institutionMapper.mapToSimpleResponse(entity.getPublishedBy().getInstitution()),
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
