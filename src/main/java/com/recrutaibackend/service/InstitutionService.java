package com.recrutaibackend.service;

import com.recrutaibackend.dto.*;
import com.recrutaibackend.mappers.InstitutionMapper;
import com.recrutaibackend.mappers.SchoolMapper;
import com.recrutaibackend.model.Industry;
import com.recrutaibackend.model.Institution;
import com.recrutaibackend.model.InstitutionSize;
import com.recrutaibackend.model.School;
import com.recrutaibackend.repository.IndustryRepository;
import com.recrutaibackend.repository.InstitutionRepository;
import com.recrutaibackend.repository.InstitutionSizeRepository;
import com.recrutaibackend.repository.SchoolRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final InstitutionMapper institutionMapper;
    private final UserService userService;
    private final InstitutionSizeRepository institutionSizeRepository;
    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;
    private final IndustryRepository industryRepository;
    private final MemberService memberService;

    public InstitutionService(
            InstitutionRepository institutionRepository,
            InstitutionMapper institutionMapper,
            UserService userService,
            InstitutionSizeRepository institutionSizeRepository,
            SchoolMapper schoolMapper,
            SchoolRepository schoolRepository,
            IndustryRepository industryRepository,
            MemberService memberService) {
        this.institutionRepository = institutionRepository;
        this.institutionMapper = institutionMapper;
        this.userService = userService;
        this.institutionSizeRepository = institutionSizeRepository;
        this.schoolMapper = schoolMapper;
        this.schoolRepository = schoolRepository;
        this.industryRepository = industryRepository;
        this.memberService = memberService;
    }

    public Institution findById(long id) {
        return institutionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Institution not found"));
    }

    @Transactional
    public Institution create(InstitutionRequest request) {
        var owner = userService.findById(request.ownerId());
        var industry = this.findIndustryByName(request.industry());
        var companySize = this.findSizeById(request.companySizeId());

        var institution = institutionMapper.mapToEntity(request, owner, industry, companySize);

        return institutionRepository.save(institution);
    }

    @Transactional
    public School createSchool(SchoolRequest request) {
        var institution = this.create(request.institution());
        var schoolSize = this.findSizeById(request.schoolSizeId());

        var school = schoolMapper.mapToEntity(institution, schoolSize);

        return schoolRepository.save(school);
    }

    public List<InstitutionResponse> findAll() {
        return institutionRepository.findAll()
                .stream()
                .map(institutionMapper::mapToResponse)
                .toList();
    }

    public List<MemberResponse> findAllMembers(long id) {
        return memberService.findAllByInstitutionId(id);
    }

    public MemberResponse createMember(long id, MemberRequest request) {
        var institution = this.findById(id);
        return memberService.create(institution, request);
    }

    private Industry findIndustryByName(String name) {
        return industryRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Industry not found"));
    }

    public List<Industry> findAllIndustries() {
        return industryRepository.findAll();
    }

    private InstitutionSize findSizeById(short id) {
        return institutionSizeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Institution size not found"));
    }

    public List<InstitutionSize> findAllSizes() {
        return institutionSizeRepository.findAll();
    }
}
