package com.recrutaibackend.profile.employment;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Employments")
public class EmploymentController {
    private final EmploymentService employmentService;

    EmploymentController(EmploymentService employmentService) {
        this.employmentService = employmentService;
    }

    @Operation(summary = "Add a new employment to user's profile")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(schema = @Schema(implementation = EmploymentResponse.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content)
    })
    @PostMapping("/users/{user_id}/employments")
    ResponseEntity<EmploymentResponse> create(
            @PathVariable("user_id") long id,
            @RequestBody @Valid EmploymentRequest request
    ) {
        var employment = employmentService.create(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(employment);
    }

    @Operation(summary = "Find all user's employments")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = EmploymentResponse.class)))
    )
    @GetMapping("/users/{user_id}/employments")
    ResponseEntity<List<EmploymentResponse>> findAllByUserId(@PathVariable("user_id") long id) {
        var employments = employmentService.findAllByUserId(id);
        return ResponseEntity.ok(employments);
    }

    @Operation(summary = "Delete employment by its id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content)
    })
    @DeleteMapping("/employments/{employment_id}")
    ResponseEntity<Void> deleteById(@PathVariable("employment_id") long id) {
        employmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
