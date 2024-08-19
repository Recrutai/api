package com.recrutaibackend.mappers;

import com.recrutaibackend.dto.VacancyRequest;
import com.recrutaibackend.dto.VacancyResponse;
import com.recrutaibackend.model.CurrencyCode;
import com.recrutaibackend.model.Member;
import com.recrutaibackend.model.Vacancy;
import org.springframework.stereotype.Service;

@Service
public class VacancyMapper {

    private final AddressMapper addressMapper;
    private final MemberMapper memberMapper;

    public VacancyMapper(AddressMapper addressMapper, MemberMapper memberMapper) {
        this.addressMapper = addressMapper;
        this.memberMapper = memberMapper;
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
                memberMapper.mapToResponse(entity.getRecruiter()),
                memberMapper.mapToResponse(entity.getPublishedBy()),
                entity.getPublishedAt(),
                memberMapper.mapToResponse(entity.getClosedBy()),
                entity.getClosedAt()
        );
    }

}
