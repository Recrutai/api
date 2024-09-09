package com.recrutaibackend.vacancy.interview;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/interviews")
@Tag(name = "Interviews")
public class InterviewController {
    private final InterviewService interviewService;
    private final InterviewMapper interviewMapper;

    InterviewController(InterviewService interviewService, InterviewMapper interviewMapper) {
        this.interviewService = interviewService;
        this.interviewMapper = interviewMapper;
    }

    @Operation(summary = "Create a new interview")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(schema = @Schema(implementation = InterviewResponse.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content)
    })
    @PostMapping
    ResponseEntity<InterviewResponse> create(@RequestBody @Valid InterviewRequest request) {
        var interview = interviewMapper.mapToResponse(interviewService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(interview);
    }

}
