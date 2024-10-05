package com.recrutai.api.vacancy.application;

import com.recrutai.api.auth.user.User;
import com.recrutai.api.auth.user.UserMapper;
import com.recrutai.api.organization.OrganizationMapper;
import com.recrutai.api.vacancy.Vacancy;
import com.recrutai.api.vacancy.VacancyMapper;
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
                request.getExpectedSalary(),
                request.getCurrency()
        );
    }

    public ApplicationResponse mapToResponse(Application entity) {
        return new ApplicationResponse(
                entity.getId(),
                userMapper.mapToResponse(entity.getCandidate()),
                vacancyMapper.mapToResponse(entity.getVacancy()),
                entity.getExpectedSalary(),
                entity.getCurrencyCode(),
                entity.getAppliedAt()
        );
    }

}
