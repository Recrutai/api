package com.recrutaibackend.vacancy;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {

    private final VacancyService vacancyService;
    private final VacancyMapper vacancyMapper;

    VacancyController(VacancyService vacancyService, VacancyMapper vacancyMapper) {
        this.vacancyService = vacancyService;
        this.vacancyMapper = vacancyMapper;
    }

    @PostMapping
    ResponseEntity<VacancyResponse> create(@RequestBody @Valid VacancyRequest vacancyRequest) {
        var vacancy = vacancyMapper.mapToResponse(vacancyService.create(vacancyRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(vacancy);
    }

    @GetMapping
    ResponseEntity<List<VacancyResponse>> findAll() {
        var vacancies = vacancyService.findAll()
                .stream()
                .map(vacancyMapper::mapToResponse)
                .toList();
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("/{id}")
    ResponseEntity<VacancyResponse> findById(@PathVariable long id) {
        var vacancy = vacancyMapper.mapToResponse(vacancyService.findById(id));
        return ResponseEntity.ok(vacancy);
    }

    @GetMapping("/search")
    ResponseEntity<List<VacancyResponse>> findAllByTitle(@RequestParam String title) {
        var response = vacancyService.findAllByTitle(title);
        return ResponseEntity.ok(response);
    }

}