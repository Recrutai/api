package com.recrutaibackend.controller;

import com.recrutaibackend.dto.VacancyRequest;
import com.recrutaibackend.dto.VacancyResponse;
import com.recrutaibackend.service.VacancyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {

    private final VacancyService vacancyService;

    VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @PostMapping
    ResponseEntity<VacancyResponse> create(@RequestBody VacancyRequest vacancyRequest) {
        var response = vacancyService.create(vacancyRequest);
        return ResponseEntity.ok(response);
    }

}
