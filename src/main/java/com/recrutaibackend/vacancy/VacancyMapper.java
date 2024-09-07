package com.recrutaibackend.vacancy;

import com.recrutaibackend.address.AddressMapper;
import com.recrutaibackend.institution.member.Member;
import com.recrutaibackend.shared.CurrencyCode;
import org.springframework.stereotype.Service;

@Service
public class VacancyMapper {
    private final AddressMapper addressMapper;

    public VacancyMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public Vacancy mapToEntity(VacancyRequest request, Member recruiter, Member publishedBy) {
        return new Vacancy(
                request.title(),
                request.description(),
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
                entity.getTitle(),
                entity.getDescription(),
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
