package com.recrutaibackend.profile.employment;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmploymentController {
    private final EmploymentService employmentService;

    EmploymentController(EmploymentService employmentService) {
        this.employmentService = employmentService;
    }

    @PostMapping("/users/{user_id}/employments")
    ResponseEntity<EmploymentResponse> create(
            @PathVariable("user_id") long id,
            @RequestBody @Valid EmploymentRequest request
    ) {
        var employment = employmentService.create(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(employment);
    }

    @GetMapping("/users/{user_id}/employments")
    ResponseEntity<List<EmploymentResponse>> findAllByUserId(@PathVariable("user_id") long id) {
        var employments = employmentService.findAllByUserId(id);
        return ResponseEntity.ok(employments);
    }

    @DeleteMapping("/employments/{employment_id}")
    ResponseEntity<Void> deleteById(@PathVariable("employment_id") long id) {
        employmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
