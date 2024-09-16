package com.recrutai.api.institution;

import com.recrutai.api.institution.member.MemberRequest;
import com.recrutai.api.institution.member.MemberResponse;
import com.recrutai.api.institution.member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/institutions")
@Tag(name = "Institutions")
public class InstitutionController {
    private final InstitutionService institutionService;
    private final InstitutionMapper institutionMapper;
    private final MemberService memberService;

    InstitutionController(
            InstitutionService institutionService,
            InstitutionMapper institutionMapper,
            MemberService memberService
    ) {
        this.institutionService = institutionService;
        this.institutionMapper = institutionMapper;
        this.memberService = memberService;
    }

    @Operation(summary = "Create a new institution")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content)
    })
    @PostMapping
    ResponseEntity<InstitutionResponse> create(@Valid @RequestBody InstitutionRequest request) {
        var institution = institutionMapper.mapToResponse(institutionService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(institution);
    }

    @Operation(summary = "Find an institution by its id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content)
    })
    @GetMapping("/{institution_id}")
    ResponseEntity<InstitutionResponse> findById(@PathVariable("institution_id") long id) {
        var institution = institutionMapper.mapToResponse(institutionService.findById(id));
        return ResponseEntity.ok(institution);
    }

    @Operation(summary = "Search through institutions")
    @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true)
    @GetMapping
    ResponseEntity<List<InstitutionSummaryResponse>> search(@RequestParam Optional<String> name) {
        var institutions = institutionService.search(name);
        return ResponseEntity.ok(institutions);
    }

    @Operation(summary = "Add a new member to the institution")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content)
    })
    @PostMapping("/{institution_id}/members")
    ResponseEntity<MemberResponse> createMember(
            @PathVariable("institution_id") long id,
            @Valid @RequestBody MemberRequest request
    ) {
        var member = memberService.create(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @Operation(summary = "Find all institution's members")
    @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true)
    @GetMapping("/{institution_id}/members")
    ResponseEntity<List<MemberResponse>> findAllMembers(@PathVariable("institution_id") long id) {
        var members = memberService.findAllByInstitutionId(id);
        return ResponseEntity.ok(members);
    }

}
