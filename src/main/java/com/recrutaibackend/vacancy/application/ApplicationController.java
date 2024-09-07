package com.recrutaibackend.vacancy.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApplicationController {
    private final ApplicationService applicationService;

    ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/users/{user_id}/applications")
    ResponseEntity<List<ApplicationResponse>> findAllByUserId(@PathVariable("user_id") long id) {
        var applications = applicationService.findAllByUserId(id);
        return ResponseEntity.ok(applications);
    }

}
