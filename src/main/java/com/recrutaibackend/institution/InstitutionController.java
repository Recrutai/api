package com.recrutaibackend.institution;

import com.recrutaibackend.institution.member.MemberRequest;
import com.recrutaibackend.institution.member.MemberResponse;
import com.recrutaibackend.institution.school.SchoolMapper;
import com.recrutaibackend.institution.school.SchoolRequest;
import com.recrutaibackend.institution.school.SchoolResponse;
import com.recrutaibackend.institution.industry.Industry;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/institutions")
public class InstitutionController {

    private final InstitutionService service;
    private final InstitutionMapper institutionMapper;
    private final SchoolMapper schoolMapper;

    InstitutionController(InstitutionService service, InstitutionMapper institutionMapper, SchoolMapper schoolMapper) {
        this.service = service;
        this.institutionMapper = institutionMapper;
        this.schoolMapper = schoolMapper;
    }

    @PostMapping
    ResponseEntity<InstitutionResponse> create(@Valid @RequestBody InstitutionRequest request) {
        var institution = institutionMapper.mapToResponse(service.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(institution);
    }

    @PostMapping("/school")
    ResponseEntity<SchoolResponse> createSchool(@Valid @RequestBody SchoolRequest request) {
        var school = schoolMapper.mapToResponse(service.createSchool(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(school);
    }

    @GetMapping("/{id}")
    ResponseEntity<InstitutionResponse> findById(@PathVariable long id) {
        var institution = institutionMapper.mapToResponse(service.findById(id));
        return ResponseEntity.ok(institution);
    }

    @GetMapping
    ResponseEntity<List<InstitutionResponse>> findAll() {
        var institutions = service.findAll();
        return ResponseEntity.ok(institutions);
    }

    @PostMapping("/{id}/members")
    ResponseEntity<MemberResponse> createMember(@PathVariable long id, @Valid @RequestBody MemberRequest request) {
        var member = service.createMember(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @GetMapping("/{id}/members")
    ResponseEntity<List<MemberResponse>> findAllMembers(@PathVariable long id) {
        var members = service.findAllMembers(id);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/industries")
    ResponseEntity<List<Industry>> findAllIndustries() {
        var industries = service.findAllIndustries();
        return ResponseEntity.ok(industries);
    }

    @GetMapping("/sizes")
    ResponseEntity<List<InstitutionSize>> findSizes() {
        var sizes = service.findAllSizes();
        return ResponseEntity.ok(sizes);
    }
}