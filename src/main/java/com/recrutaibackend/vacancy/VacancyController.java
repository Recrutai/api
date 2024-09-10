package com.recrutaibackend.vacancy;

import com.recrutaibackend.vacancy.application.ApplicationRequest;
import com.recrutaibackend.vacancy.application.ApplicationResponse;
import com.recrutaibackend.vacancy.application.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vacancies")
@Tag(name = "Vacancies")
public class VacancyController {
    private final VacancyService vacancyService;
    private final ApplicationService applicationService;

    VacancyController(VacancyService vacancyService, ApplicationService applicationService) {
        this.vacancyService = vacancyService;
        this.applicationService = applicationService;
    }

    @Operation(summary = "Publish a new vacancy")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(schema = @Schema(implementation = VacancyResponse.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content)
    })
    @PostMapping
    ResponseEntity<VacancyResponse> create(@RequestBody @Valid VacancyRequest request) {
        var vacancy = vacancyService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(vacancy);
    }

    @Operation(summary = "Find all vacancies")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = VacancyResponse.class)))
    )
    @GetMapping
    ResponseEntity<List<VacancyResponse>> findAll() {
        var vacancies = vacancyService.findAll();
        return ResponseEntity.ok(vacancies);
    }

    @Operation(summary = "Find all vacancies by title")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = VacancyResponse.class)))
    )
    @GetMapping("/search")
    ResponseEntity<List<VacancyResponse>> findAllByTitle(@RequestParam String title) {
        var vacancies = vacancyService.findAllByTitle(title);
        return ResponseEntity.ok(vacancies);
    }

    @Operation(summary = "Find all vacancy's applications")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ApplicationResponse.class)))
    )
    @GetMapping("/{vacancy_id}/applications")
    ResponseEntity<List<ApplicationResponse>> findAllApplications(@PathVariable("vacancy_id") long id) {
        var vacancies = applicationService.findAllByVacancyId(id);
        return ResponseEntity.ok(vacancies);
    }

    @Operation(summary = "Apply to a vacancy")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(schema = @Schema(implementation = ApplicationResponse.class))),
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

}
