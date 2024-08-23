package com.recrutaibackend.vacancy.application;

import com.recrutaibackend.auth.user.UserService;
import com.recrutaibackend.vacancy.VacancyService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @Transactional
    public Application create(ApplicationRequest request) {
        var user = userService.findById(request.candidateId());
        var vacancy = vacancyService.findById(request.vacancyId());

        var application = applicationMapper.mapToEntity(request, user, vacancy);

        return applicationRepository.save(application);
    }

    public Application findById(long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
    }

    public List<ApplicationResponse> findAllByUserId(long id) {
        var user = userService.findById(id);
        var applications = applicationRepository.findAllByCandidate(user);
        return applications
                .stream()
                .map(applicationMapper::mapToResponse)
                .toList();
    }
}
