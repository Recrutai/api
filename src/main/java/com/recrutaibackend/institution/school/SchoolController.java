package com.recrutaibackend.institution.school;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/schools")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    ResponseEntity<SchoolResponse> create(@RequestBody @Valid SchoolRequest schoolRequest) {
        var school = schoolService.create(schoolRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(school);
    }

}
