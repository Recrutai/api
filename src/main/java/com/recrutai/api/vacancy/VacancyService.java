package com.recrutai.api.vacancy;

import com.recrutai.api.organization.OrganizationService;
import com.recrutai.api.organization.member.MemberService;
import com.recrutai.api.shared.EmploymentType;
import com.recrutai.api.shared.WorkModel;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;
    private final MemberService memberService;
    private final OrganizationService organizationService;

    public VacancyService(
            VacancyRepository vacancyRepository,
            VacancyMapper vacancyMapper,
            MemberService memberService,
            OrganizationService organizationService) {
        this.vacancyRepository = vacancyRepository;
        this.vacancyMapper = vacancyMapper;
        this.memberService = memberService;
        this.organizationService = organizationService;
    }

    @Transactional
    public VacancyResponse create(VacancyRequest request) {
        var organization = organizationService.findById(request.getOrganizationId());
        var publisher = memberService.findById(request.getPublishedById());
        var recruiter = memberService.findById(request.getRecruiterId());

        var vacancy = vacancyMapper.mapToEntity(request, organization, recruiter, publisher);
        var savedVacancy = vacancyRepository.save(vacancy);

        return vacancyMapper.mapToResponse(savedVacancy);
    }

    public Vacancy findById(long id) {
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vacancy not found"));
    }

    public List<VacancySummaryResponse> search(
            Optional<String> opTitle,
            Long locationId,
            Long organizationId,
            WorkModel workModel,
            EmploymentType employmentType
    ) {
        var title = opTitle.map(String::strip).orElse("");
        return vacancyRepository.search(title, locationId, organizationId, workModel, employmentType);
    }

    public List<VacancyResponse> findAllByMember(long id) {
        var member = memberService.findById(id);
        var vacancies = vacancyRepository.findAllByRecruiter(member);
        return vacancies
                .stream()
                .map(vacancyMapper::mapToResponse)
                .toList();
    }

}
