package com.recrutai.api.profile.employment;

import com.recrutai.api.auth.user.UserService;
import com.recrutai.api.institution.Institution;
import com.recrutai.api.institution.InstitutionService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmploymentService {
    private final EmploymentRepository employmentRepository;
    private final EmploymentMapper employmentMapper;
    private final UserService userService;
    private final InstitutionService institutionService;

    public EmploymentService(
            EmploymentRepository employmentRepository,
            EmploymentMapper employmentMapper,
            UserService userService,
            InstitutionService institutionService
    ) {
        this.employmentRepository = employmentRepository;
        this.employmentMapper = employmentMapper;
        this.userService = userService;
        this.institutionService = institutionService;
    }

    @Transactional
    public EmploymentResponse create(long userId, EmploymentRequest request) {
        var user = userService.findById(userId);
        var institution = getInstitution(request);

        var employment = employmentMapper.mapToEntity(request, user, institution);
        var savedEmployment = employmentRepository.save(employment);

        return employmentMapper.mapToResponse(savedEmployment);
    }

    private Institution getInstitution(EmploymentRequest request) {
        if (request.institutionId() != null) {
            return institutionService.findById(request.institutionId());
        }
        if (request.fallbackInstitutionName() == null) {
            var msg = "The institution's name is required when the id of an existing one is not provided";
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, msg);
        }
        return null;
    }

    public List<EmploymentResponse> findAllByUserId(long id) {
        return employmentRepository.findAllWithAddressByUserId(id)
                .stream()
                .map(employmentMapper::mapToResponse)
                .toList();
    }

    public void deleteById(long id) {
        var employment = this.findById(id);
        employmentRepository.delete(employment);
    }

    private Employment findById(long id) {
        return employmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employment not found"));
    }

}
