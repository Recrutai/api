package com.recrutaibackend.profile.employment;

import com.recrutaibackend.institution.InstitutionService;
import com.recrutaibackend.auth.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmploymentService {
    private final EmploymentRepository employmentRepository;
    private final EmploymentMapper employmentMapper;
    private final EmploymentTypeRepository employmentTypeRepository;
    private final UserService userService;
    private final InstitutionService institutionService;

    public EmploymentService(
            EmploymentRepository employmentRepository,
            EmploymentMapper employmentMapper,
            EmploymentTypeRepository employmentTypeRepository,
            UserService userService,
            InstitutionService institutionService
    ) {
        this.employmentRepository = employmentRepository;
        this.employmentMapper = employmentMapper;
        this.employmentTypeRepository = employmentTypeRepository;
        this.userService = userService;
        this.institutionService = institutionService;
    }


    @Transactional
    public EmploymentResponse create(EmploymentRequest request) {
        var user = userService.findById(request.userId());
        var institution = institutionService.findById(request.institutionId());
        var type = this.findTypeByName(request.type());

        var employment = employmentMapper.mapToEntity(request, user, institution, type);
        var savedEmployment = employmentRepository.save(employment);

        return employmentMapper.mapToResponse(savedEmployment);
    }

    public List<EmploymentResponse> findAllByUserId(long id) {
        var user = userService.findById(id);
        return employmentRepository.findAllByUser(user)
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

    private EmploymentType findTypeByName(String name) {
        return employmentTypeRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Type not found"));
    }
}
