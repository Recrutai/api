package com.recrutai.api.institution.school;

import com.recrutai.api.institution.InstitutionService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    private final InstitutionService institutionService;

    public SchoolService(
            SchoolRepository schoolRepository,
            SchoolMapper schoolMapper,
            InstitutionService institutionService
    ) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
        this.institutionService = institutionService;
    }

    @Transactional
    public SchoolResponse create(SchoolRequest request) {
        var institution = institutionService.create(request.getInstitution());

        var school = schoolMapper.mapToEntity(request, institution);
        var savedSchool = schoolRepository.save(school);

        return schoolMapper.mapToResponse(savedSchool);
    }

    public School findById(long id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "School not found"));
    }

}
