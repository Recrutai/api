package com.recrutai.api.vacancy.application;

import com.recrutai.api.auth.user.UserService;
import com.recrutai.api.vacancy.VacancyService;
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
    public ApplicationResponse create(long vacancyId, ApplicationRequest request) {
        var user = userService.findById(request.candidateId());
        var vacancy = vacancyService.findById(vacancyId);

        var application = applicationMapper.mapToEntity(request, user, vacancy);
        var savedApplication = applicationRepository.save(application);

        return applicationMapper.mapToResponse(savedApplication);
    }

    public Application findById(long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
    }

    public List<ApplicationResponse> findAllByUserId(long id) {
        return applicationRepository.findAllByCandidateId(id)
                .stream()
                .map(applicationMapper::mapToResponse)
                .toList();
    }

    public List<ApplicationResponse> findAllByVacancyId(long id) {
        return applicationRepository.findAllByVacancyId(id)
                .stream()
                .map(applicationMapper::mapToResponse)
                .toList();
    }

}
