package com.recrutaibackend.service;

import com.recrutaibackend.dto.ApplicationRequest;
import com.recrutaibackend.dto.ApplicationResponse;
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

    public ApplicationResponse Application(ApplicationRequest request) {

        var user = userService.findUserById(request.candidate().getId());
        var vacancy = vacancyService.findVacancy(request.vacancy().getId());

        var application = applicationMapper.mapToEntity(request, user, vacancy);
        var applicationSaved = applicationRepository.save(application);

        return applicationMapper.mapToResponse(applicationSaved);
    }

    public Application findById(int id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
    }
}
