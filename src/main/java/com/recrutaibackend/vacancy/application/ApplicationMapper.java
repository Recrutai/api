package com.recrutaibackend.vacancy.application;

import com.recrutaibackend.auth.user.UserMapper;
import com.recrutaibackend.vacancy.VacancyMapper;
import com.recrutaibackend.shared.CurrencyCode;
import com.recrutaibackend.auth.user.User;
import com.recrutaibackend.vacancy.Vacancy;
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
