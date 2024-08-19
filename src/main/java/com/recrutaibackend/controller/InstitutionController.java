package com.recrutaibackend.controller;

import com.recrutaibackend.dto.InstitutionRequest;
import com.recrutaibackend.dto.InstitutionResponse;
import com.recrutaibackend.dto.SchoolRequest;
import com.recrutaibackend.dto.SchoolResponse;
import com.recrutaibackend.mappers.InstitutionMapper;
import com.recrutaibackend.mappers.SchoolMapper;
import com.recrutaibackend.model.InstitutionSize;
import com.recrutaibackend.service.InstitutionService;
import jakarta.validation.Valid;
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
        return ResponseEntity.ok(institution);
    }

    @PostMapping("/school")
    ResponseEntity<SchoolResponse> createSchool(@Valid @RequestBody SchoolRequest request) {
        var school = schoolMapper.mapToResponse(service.createSchool(request));
        return ResponseEntity.ok(school);
    }

    @GetMapping("/{id}")
    ResponseEntity<InstitutionResponse> findById(@PathVariable Long id) {
        var institution = institutionMapper.mapToResponse(service.findById(id));
        return ResponseEntity.ok(institution);
    }

    @GetMapping
    ResponseEntity<List<InstitutionResponse>> findAll() {
        var institutions = service.findAll();
        return ResponseEntity.ok(institutions);
    }

    @GetMapping("/sizes")
    ResponseEntity<List<InstitutionSize>> findSizes() {
        var sizes = service.findAllSizes();
        return ResponseEntity.ok(sizes);
    }
}
