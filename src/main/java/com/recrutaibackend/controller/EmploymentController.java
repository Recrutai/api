package com.recrutaibackend.controller;

import com.recrutaibackend.dto.EmploymentRequest;
import com.recrutaibackend.dto.EmploymentResponse;
import com.recrutaibackend.service.EmploymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employments")
public class EmploymentController {

    private final EmploymentService employmentService;

    EmploymentController(EmploymentService employmentService) {
        this.employmentService = employmentService;
    }

    @PostMapping
    ResponseEntity<EmploymentResponse> create(@RequestBody @Valid EmploymentRequest request) {
        var employment = employmentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(employment);
    }

    @GetMapping("/{userId}")
    ResponseEntity<List<EmploymentResponse>> findAllByUserId(@PathVariable @NotNull long userId) {
        var employments = employmentService.findAllByUserId(userId);
        return ResponseEntity.ok(employments);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable @NotNull long id) {
        employmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
