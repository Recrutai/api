package com.recrutai.api.vacancy;

import com.recrutai.api.shared.EmploymentType;
import com.recrutai.api.shared.WorkModel;
import com.recrutai.api.vacancy.application.ApplicationRequest;
import com.recrutai.api.vacancy.application.ApplicationResponse;
import com.recrutai.api.vacancy.application.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vacancies")
@Tag(name = "Vacancies")
public class VacancyController {
    private final VacancyService vacancyService;
    private final ApplicationService applicationService;
    private final VacancyMapper vacancyMapper;

    VacancyController(VacancyService vacancyService, ApplicationService applicationService, VacancyMapper vacancyMapper) {
        this.vacancyService = vacancyService;
        this.applicationService = applicationService;
        this.vacancyMapper = vacancyMapper;
    }

    @Operation(summary = "Publish a new vacancy")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content)
    })
    @PostMapping
    ResponseEntity<VacancyResponse> create(@RequestBody @Valid VacancyRequest request) {
        var vacancy = vacancyService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(vacancy);
    }

    @Operation(summary = "Search through vacancies")
    @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true)
    @GetMapping
    ResponseEntity<List<VacancySummaryResponse>> search(
            @RequestParam Optional<String> title,
            @RequestParam(required = false) Long locationId,
            @RequestParam(required = false) Long organizationId,
            @RequestParam(required = false) WorkModel workModel,
            @RequestParam(required = false) EmploymentType employmentType
    ) {
        var vacancies = vacancyService.search(title, locationId, organizationId, workModel, employmentType);
        return ResponseEntity.ok(vacancies);
    }

    @Operation(summary = "Find all vacancy's applications")
    @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true)
    @GetMapping("/{vacancy_id}/applications")
    ResponseEntity<List<ApplicationResponse>> findAllApplications(@PathVariable("vacancy_id") long id) {
        var vacancies = applicationService.findAllByVacancyId(id);
        return ResponseEntity.ok(vacancies);
    }

    @Operation(summary = "Apply to a vacancy")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content)
    })
    @PostMapping("/{vacancy_id}/applications")
    ResponseEntity<ApplicationResponse> createApplication(
            @PathVariable("vacancy_id") long id,
            @RequestBody @Valid ApplicationRequest request
    ) {
        var application = applicationService.create(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(application);
    }

    @Operation(summary = "Find vacancy by id")
    @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true)
    @GetMapping("/{id}")
    ResponseEntity<VacancyResponse> findById(@PathVariable("id") long id) {
        var vacancy = vacancyService.findById(id);
        return ResponseEntity.ok(vacancyMapper.mapToResponse(vacancy));
    }

    @Operation(summary = "Find all vacancy by member id")
    @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true)
    @GetMapping("/member/{id}")
    ResponseEntity<List<VacancyResponse>> findAllByMemberId(@PathVariable("id") long id) {
        var vacancies = vacancyService.findAllByMember(id);
        return ResponseEntity.ok(vacancies);
    }

}
