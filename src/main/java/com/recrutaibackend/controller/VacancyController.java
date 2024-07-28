package com.recrutaibackend.controller;

import com.recrutaibackend.dto.VacancyRequest;
import com.recrutaibackend.dto.VacancyResponse;
import com.recrutaibackend.service.VacancyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    ResponseEntity<List<VacancyResponse>> getAllVacancys() {
        var vacancys = vacancyService.getAllVacancys();
        return ResponseEntity.ok(vacancys);
    }

    @GetMapping("/{id}")
    ResponseEntity<VacancyResponse> vacancyDetails(@PathVariable Integer id) {
        var response = vacancyService.vacancyDetails(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    ResponseEntity<VacancyResponse> searchVacancy(@RequestParam String title) {
        var response = vacancyService.findVacancyByTitle(title);
        return ResponseEntity.ok(response);
    }

}
