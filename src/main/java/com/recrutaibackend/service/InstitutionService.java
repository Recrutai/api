package com.recrutaibackend.service;

import com.recrutaibackend.dto.InstitutionRequest;
import com.recrutaibackend.dto.InstitutionResponse;
import com.recrutaibackend.dto.SchoolRequest;
import com.recrutaibackend.mappers.InstitutionMapper;
import com.recrutaibackend.mappers.SchoolMapper;
import com.recrutaibackend.model.Institution;
import com.recrutaibackend.model.InstitutionSize;
import com.recrutaibackend.model.School;
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
    private final UserService userService;
    private final IndustryService industryService;
    private final InstitutionSizeRepository institutionSizeRepository;
    private final InstitutionMapper institutionMapper;
    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;

    public InstitutionService(InstitutionRepository institutionRepository, UserService userService, IndustryService industryService, InstitutionMapper institutionMapper, InstitutionSizeRepository institutionSizeRepository, InstitutionMapper institutionMapper1, SchoolMapper schoolMapper, SchoolRepository schoolRepository) {
        this.institutionRepository = institutionRepository;
        this.userService = userService;
        this.industryService = industryService;
        this.institutionSizeRepository = institutionSizeRepository;
        this.institutionMapper = institutionMapper1;
        this.schoolMapper = schoolMapper;
        this.schoolRepository = schoolRepository;
    }

    public Institution findById(long id) {
        return institutionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Institution not found"));
    }

    @Transactional
    public Institution create(InstitutionRequest request) {
        var owner = userService.findById(request.ownerId());
        var industry = industryService.findByName(request.industry());
        var companySize = this.findSizeById(request.companySizeId());

        var institution = institutionMapper.mapToEntity(request, owner, industry, companySize);

        return institutionRepository.save(institution);
    }

    private InstitutionSize findSizeById(short id) {
        return institutionSizeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Institution size not found"));
    }

    public List<InstitutionResponse> findAll() {
        return institutionRepository.findAll()
                .stream()
                .map(institutionMapper::mapToResponse)
                .toList();
    }

    @Transactional
    public School createSchool(SchoolRequest request) {
        var institution = this.create(request.institution());
        var schoolSize = this.findSizeById(request.schoolSizeId());

        var school = schoolMapper.mapToEntity(institution, schoolSize);

        return schoolRepository.save(school);
    }

    public List<InstitutionSize> findAllSizes() {
        return institutionSizeRepository.findAll();
    }
}
