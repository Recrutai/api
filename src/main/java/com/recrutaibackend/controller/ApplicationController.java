package com.recrutaibackend.controller;

import com.recrutaibackend.dto.ApplicationRequest;
import com.recrutaibackend.dto.ApplicationResponse;
import com.recrutaibackend.mappers.ApplicationMapper;
import com.recrutaibackend.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationMapper applicationMapper;

    ApplicationController(ApplicationService applicationService,
                          ApplicationMapper applicationMapper) {
        this.applicationService = applicationService;
        this.applicationMapper = applicationMapper;
    }

    @PostMapping
    ResponseEntity<ApplicationResponse> create(@RequestBody @Valid ApplicationRequest applicationRequest) {
        var application = applicationMapper.mapToResponse(applicationService.create(applicationRequest));
        return ResponseEntity.ok(application);
    }

    @GetMapping("/{id}")
    ResponseEntity<List<ApplicationResponse>> getAllByUser(@PathVariable @Valid int id) {
        var applications = applicationService.getAllApplicationsByUser(id);
        return ResponseEntity.ok(applications);
    }

}
