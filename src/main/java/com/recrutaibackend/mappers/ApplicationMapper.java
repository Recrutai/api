package com.recrutaibackend.mappers;

import com.recrutaibackend.dto.ApplicationRequest;
import com.recrutaibackend.dto.ApplicationResponse;
import com.recrutaibackend.model.Application;
import com.recrutaibackend.model.CurrencyCode;
import com.recrutaibackend.model.User;
import com.recrutaibackend.model.Vacancy;
import org.springframework.stereotype.Service;

@Service
public class ApplicationMapper {

    private final UserMapper userMapper;
    private final VacancyMapper vacancyMapper;

    public ApplicationMapper(UserMapper userMapper, VacancyMapper vacancyMapper) {
        this.userMapper = userMapper;
        this.vacancyMapper = vacancyMapper;
    }

    public Application mapToEntity(ApplicationRequest request, User candidate, Vacancy vacancy) {
        return new Application(
                candidate,
                vacancy,
                request.expectedSalary(),
                CurrencyCode.BRL
        );
    }

    public ApplicationResponse mapToResponse(Application application) {
        return new ApplicationResponse(
                application.getId(),
                userMapper.mapToResponse(application.getCandidate()),
                vacancyMapper.mapToResponse(application.getVacancy()),
                application.getExpectedSalary(),
                application.getCurrencyCode().toString(),
                application.getAppliedAt()
        );
    }

}
