package com.recrutaibackend.controller;

import com.recrutaibackend.dto.InterviewRequest;
import com.recrutaibackend.dto.InterviewResponse;
import com.recrutaibackend.service.InterviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping
    ResponseEntity<InterviewResponse> createInterview(@RequestBody InterviewRequest request) {
        var interview = interviewService.createInterview(request);
        return ResponseEntity.ok(interview);
    }

}
