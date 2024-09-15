package com.recrutai.api.industry;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/industries")
@Tag(name = "Industries")
public class IndustryController {
    private final IndustryService industryService;

    public IndustryController(IndustryService industryService) {
        this.industryService = industryService;
    }

    @Operation(summary = "Search through available industries")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(examples = @ExampleObject("[\"Technology\", \"Education\", \"Administration\"]"))
    )
    @GetMapping
    ResponseEntity<List<String>> findAllFiltered(@RequestParam Optional<String> name) {
        var industries = industryService.findAllFiltered(name);
        return ResponseEntity.ok(industries);
    }

}
