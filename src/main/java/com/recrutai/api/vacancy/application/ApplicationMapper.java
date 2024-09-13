package com.recrutai.api.vacancy.application;

import com.recrutai.api.auth.user.User;
import com.recrutai.api.shared.CurrencyCode;
import com.recrutai.api.vacancy.Vacancy;
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
