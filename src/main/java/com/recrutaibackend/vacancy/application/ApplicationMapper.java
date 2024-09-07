package com.recrutaibackend.vacancy.application;

import com.recrutaibackend.auth.user.User;
import com.recrutaibackend.shared.CurrencyCode;
import com.recrutaibackend.vacancy.Vacancy;
import org.springframework.stereotype.Service;

@Service
public class ApplicationMapper {

    public Application mapToEntity(ApplicationRequest request, User candidate, Vacancy vacancy) {
        return new Application(
                candidate,
                vacancy,
                request.expectedSalary(),
                CurrencyCode.BRL
        );
    }

    public ApplicationResponse mapToResponse(Application entity) {
        return new ApplicationResponse(
                entity.getId(),
                entity.getCandidate().getId(),
                entity.getExpectedSalary(),
                entity.getCurrencyCode().toString(),
                entity.getAppliedAt()
        );
    }

}
