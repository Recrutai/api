package com.recrutaibackend.controller;

import com.recrutaibackend.dto.ApplicationRequest;
import com.recrutaibackend.dto.ApplicationResponse;
import com.recrutaibackend.mappers.ApplicationMapper;
import com.recrutaibackend.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(application);
    }

    @GetMapping("/{userId}")
    ResponseEntity<List<ApplicationResponse>> findAllByUserId(@PathVariable long userId) {
        var applications = applicationService.findAllByUserId(userId);
        return ResponseEntity.ok(applications);
    }

}
