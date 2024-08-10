package com.recrutaibackend.service;

import com.recrutaibackend.dto.ApplicationRequest;
import com.recrutaibackend.model.Application;
import com.recrutaibackend.repository.ApplicationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserService userService;
    private final VacancyService vacancyService;
    private final ApplicationMapper applicationMapper;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            UserService userService,
            VacancyService vacancyService,
            ApplicationMapper applicationMapper
    ) {
        this.applicationRepository = applicationRepository;
        this.userService = userService;
        this.vacancyService = vacancyService;
        this.applicationMapper = applicationMapper;
    }

    public Application create(ApplicationRequest request) {
        var user = userService.findById(request.candidateId());
        var vacancy = vacancyService.findById(request.vacancyId());

        var application = applicationMapper.mapToEntity(request, user, vacancy);

        return applicationRepository.save(application);
    }

    public Application findById(int id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
    }
}
