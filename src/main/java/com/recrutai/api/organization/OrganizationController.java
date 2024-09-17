package com.recrutai.api.organization;

import com.recrutai.api.organization.member.MemberRequest;
import com.recrutai.api.organization.member.MemberResponse;
import com.recrutai.api.organization.member.MemberService;
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
@RequestMapping("/api/v1/organizations")
@Tag(name = "Organizations")
public class OrganizationController {
    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;
    private final MemberService memberService;

    OrganizationController(
            OrganizationService organizationService,
            OrganizationMapper organizationMapper,
            MemberService memberService
    ) {
        this.organizationService = organizationService;
        this.organizationMapper = organizationMapper;
        this.memberService = memberService;
    }

    @Operation(summary = "Create a new organization")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content)
    })
    @PostMapping
    ResponseEntity<OrganizationResponse> create(@Valid @RequestBody OrganizationRequest request) {
        var organization = organizationMapper.mapToResponse(organizationService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(organization);
    }

    @Operation(summary = "Find an organization by its id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content)
    })
    @GetMapping("/{organization_id}")
    ResponseEntity<OrganizationResponse> findById(@PathVariable("organization_id") long id) {
        var organization = organizationMapper.mapToResponse(organizationService.findById(id));
        return ResponseEntity.ok(organization);
    }

    @Operation(summary = "Search through organizations")
    @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true)
    @GetMapping
    ResponseEntity<List<OrganizationSummaryResponse>> search(@RequestParam Optional<String> name) {
        var organizations = organizationService.search(name);
        return ResponseEntity.ok(organizations);
    }

    @Operation(summary = "Add a new member to the organization")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content)
    })
    @PostMapping("/{organization_id}/members")
    ResponseEntity<MemberResponse> createMember(
            @PathVariable("organization_id") long id,
            @Valid @RequestBody MemberRequest request
    ) {
        var member = memberService.create(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @Operation(summary = "Find all organization's members")
    @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true)
    @GetMapping("/{organization_id}/members")
    ResponseEntity<List<MemberResponse>> findAllMembers(@PathVariable("organization_id") long id) {
        var members = memberService.findAllByOrganizationId(id);
        return ResponseEntity.ok(members);
    }

}
