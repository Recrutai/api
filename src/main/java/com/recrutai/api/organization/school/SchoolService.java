package com.recrutai.api.organization.school;

import com.recrutai.api.organization.OrganizationService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    private final OrganizationService organizationService;

    public SchoolService(
            SchoolRepository schoolRepository,
            SchoolMapper schoolMapper,
            OrganizationService organizationService
    ) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
        this.organizationService = organizationService;
    }

    @Transactional
    public SchoolResponse create(SchoolRequest request) {
        var organization = organizationService.create(request.getOrganization());

        var school = schoolMapper.mapToEntity(request, organization);
        var savedSchool = schoolRepository.save(school);

        return schoolMapper.mapToResponse(savedSchool);
    }

    public School findById(long id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "School not found"));
    }

}
