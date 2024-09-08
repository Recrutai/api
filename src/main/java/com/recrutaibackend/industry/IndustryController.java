package com.recrutaibackend.industry;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/industries")
@Tag(name = "Industries")
public class IndustryController {
    private final IndustryService industryService;

    public IndustryController(IndustryService industryService) {
        this.industryService = industryService;
    }

    @Operation(summary = "Get available industries")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(examples = @ExampleObject(value = "[\"Education\", \"Technology\"]"))
    )
    @GetMapping
    ResponseEntity<List<String>> findAll() {
        var industries = industryService.findAll();
        return ResponseEntity.ok(industries);
    }

}
