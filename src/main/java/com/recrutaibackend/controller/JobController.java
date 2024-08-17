package com.recrutaibackend.controller;

import com.recrutaibackend.dto.JobRequest;
import com.recrutaibackend.dto.JobResponse;
import com.recrutaibackend.service.JobService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    @Transactional
    ResponseEntity<JobResponse> create(@RequestBody @Valid JobRequest request) {
        var job = jobService.create(request);
        return ResponseEntity.ok(job);
    }

    @GetMapping("/{id}")
    ResponseEntity<List<JobResponse>> getAll(@PathVariable @NotNull int id) {
        var jobs = jobService.getAll(id);
        return ResponseEntity.ok(jobs);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable @NotNull int id) {
        jobService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
