package com.recrutai.api.vacancy.application;

import com.recrutai.api.auth.user.User;
import com.recrutai.api.auth.user.UserMapper;
import com.recrutai.api.vacancy.Vacancy;
import org.springframework.stereotype.Service;

@Service
public class ApplicationMapper {
    private final UserMapper userMapper;

    public ApplicationMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
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
                entity.getExpectedSalary(),
                entity.getCurrencyCode(),
                entity.getAppliedAt()
        );
    }

}
