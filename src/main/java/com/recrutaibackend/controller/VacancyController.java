package com.recrutaibackend.controller;

import com.recrutaibackend.dto.VacancyRequest;
import com.recrutaibackend.dto.VacancyResponse;
import com.recrutaibackend.service.VacancyMapper;
import com.recrutaibackend.service.VacancyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
        return ResponseEntity.ok(vacancy);
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
    ResponseEntity<VacancyResponse> findById(@PathVariable @NotNull Integer id) {
        var vacancy = vacancyMapper.mapToResponse(vacancyService.findById(id));
        return ResponseEntity.ok(vacancy);
    }

    @GetMapping("/search")
    ResponseEntity<List<VacancyResponse>> findByTitle(@RequestParam @NotBlank String title) {
        var response = vacancyService.getAllVacanciesByTitleSeach(title);
        return ResponseEntity.ok(response);
    }

}
