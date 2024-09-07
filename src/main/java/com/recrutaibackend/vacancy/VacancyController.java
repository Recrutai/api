package com.recrutaibackend.vacancy;

import com.recrutaibackend.vacancy.application.ApplicationRequest;
import com.recrutaibackend.vacancy.application.ApplicationResponse;
import com.recrutaibackend.vacancy.application.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vacancies")
public class VacancyController {
    private final VacancyService vacancyService;
    private final ApplicationService applicationService;

    VacancyController(VacancyService vacancyService, ApplicationService applicationService) {
        this.vacancyService = vacancyService;
        this.applicationService = applicationService;
    }

    @PostMapping
    ResponseEntity<VacancyResponse> create(@RequestBody @Valid VacancyRequest request) {
        var vacancy = vacancyService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(vacancy);
    }

    @GetMapping
    ResponseEntity<List<VacancyResponse>> findAllByTitle(@RequestParam String title) {
        var response = vacancyService.findAllByTitle(title);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{vacancy_id}/applications")
    ResponseEntity<List<ApplicationResponse>> findAllApplications(@PathVariable("vacancy_id") long id) {
        var vacancies = applicationService.findAllByVacancyId(id);
        return ResponseEntity.ok(vacancies);
    }

    @PostMapping("/{vacancy_id}/applications")
    ResponseEntity<ApplicationResponse> createApplication(
            @PathVariable("vacancy_id") long id,
            @RequestBody @Valid ApplicationRequest request
    ) {
        var application = applicationService.create(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(application);
    }

}
