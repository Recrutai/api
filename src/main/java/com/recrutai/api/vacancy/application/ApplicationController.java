package com.recrutai.api.vacancy.application;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Operation(summary = "Find all user's applications")
    @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true)
    @GetMapping("/users/{user_id}/applications")
    ResponseEntity<List<ApplicationResponse>> findAllByUserId(@PathVariable("user_id") long id) {
        var applications = applicationService.findAllByUserId(id);
        return ResponseEntity.ok(applications);
    }

}
