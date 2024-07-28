package com.recrutaibackend.service;

import com.recrutaibackend.dto.VacancyRequest;
import com.recrutaibackend.dto.VacancyResponse;
import com.recrutaibackend.model.Member;
import com.recrutaibackend.model.Vacancy;
import org.springframework.stereotype.Service;

@Service
public class VacancyMapper {

    public Vacancy mapToEntity(VacancyRequest request, Member recruiter, Member publisher) {
        return new Vacancy(
                request.title(),
                request.description(),
                request.workModel(),
                this.convertAvgSalaryToCents(request.avgSalary()),
                request.positions(),
                recruiter,
                publisher
        );
    }

    public VacancyResponse mapToResponse(Vacancy entity) {
        return new VacancyResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getWorkModel(),
                this.convertAvgSalaryFromCents(entity.getAvgSalary()),
                entity.getPositions(),
                entity.getApplications(),
                entity.getRecruiter().getId(),
                entity.getPublishedBy().getId()
        );
    }

    private int convertAvgSalaryToCents(double avgSalary) {
        return (int) (avgSalary * 100);
    }

    private double convertAvgSalaryFromCents(int avgSalary) {
        return avgSalary / 100.0;
    }

}
