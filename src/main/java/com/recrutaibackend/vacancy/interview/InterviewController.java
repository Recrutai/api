package com.recrutaibackend.vacancy.interview;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;
    private final InterviewMapper interviewMapper;

    InterviewController(InterviewService interviewService, InterviewMapper interviewMapper) {
        this.interviewService = interviewService;
        this.interviewMapper = interviewMapper;
    }

    @PostMapping
    ResponseEntity<InterviewResponse> create(@RequestBody @Valid InterviewRequest interviewRequest) {
        var interview = interviewMapper.mapToResponse(interviewService.create(interviewRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(interview);
    }

}
