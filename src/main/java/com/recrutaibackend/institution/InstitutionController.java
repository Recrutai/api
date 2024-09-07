package com.recrutaibackend.institution;

import com.recrutaibackend.institution.member.MemberRequest;
import com.recrutaibackend.institution.member.MemberResponse;
import com.recrutaibackend.institution.member.MemberService;
import com.recrutaibackend.vacancy.VacancyResponse;
import com.recrutaibackend.vacancy.VacancyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/institutions")
public class InstitutionController {
    private final InstitutionService institutionService;
    private final InstitutionMapper institutionMapper;
    private final MemberService memberService;
    private final VacancyService vacancyService;

    InstitutionController(
            InstitutionService institutionService,
            InstitutionMapper institutionMapper,
            MemberService memberService,
            VacancyService vacancyService
    ) {
        this.institutionService = institutionService;
        this.institutionMapper = institutionMapper;
        this.memberService = memberService;
        this.vacancyService = vacancyService;
    }

    @PostMapping
    ResponseEntity<InstitutionResponse> create(@Valid @RequestBody InstitutionRequest request) {
        var institution = institutionMapper.mapToResponse(institutionService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(institution);
    }

    @GetMapping("/{institution_id}")
    ResponseEntity<InstitutionResponse> findById(@PathVariable("institution_id") long id) {
        var institution = institutionMapper.mapToResponse(institutionService.findById(id));
        return ResponseEntity.ok(institution);
    }

    @GetMapping
    ResponseEntity<List<InstitutionResponse>> findAll() {
        var institutions = institutionService.findAll();
        return ResponseEntity.ok(institutions);
    }

    @PostMapping("/{institution_id}/members")
    ResponseEntity<MemberResponse> createMember(
            @PathVariable("institution_id") long id,
            @Valid @RequestBody MemberRequest request
    ) {
        var member = memberService.create(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @GetMapping("/{institution_id}/members")
    ResponseEntity<List<MemberResponse>> findAllMembers(@PathVariable("institution_id") long id) {
        var members = memberService.findAllByInstitutionId(id);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{institution_id}/vacancies")
    ResponseEntity<List<VacancyResponse>> findAllVacancies(@PathVariable("institution_id") long id) {
        var vacancies = vacancyService.findAllByInstitutionId(id);
        return ResponseEntity.ok(vacancies);
    }

}
