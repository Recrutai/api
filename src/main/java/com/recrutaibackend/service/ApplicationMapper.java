package com.recrutaibackend.service;

import com.recrutaibackend.dto.ApplicationRequest;
import com.recrutaibackend.dto.ApplicationResponse;
import com.recrutaibackend.model.Application;
import com.recrutaibackend.model.User;
import com.recrutaibackend.model.Vacancy;
import org.springframework.stereotype.Service;

@Service
public class ApplicationMapper {

    public Application mapToEntity(ApplicationRequest request, User candidate, Vacancy vacancy) {
        return new Application(
                candidate,
                vacancy,
                request.expectedSalary(),
                request.status()
        );
    }

    public ApplicationResponse mapToResponse(Application application) {
        return new ApplicationResponse(
                application.getId(),
                application.getCandidate(),
                application.getVacancy(),
                application.getExpectedSalary(),
                application.getStatus(),
                application.getAppliedAt()
        );
    }
}
