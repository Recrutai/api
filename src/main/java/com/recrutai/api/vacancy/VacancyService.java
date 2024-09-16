package com.recrutai.api.vacancy;

import com.recrutai.api.institution.InstitutionService;
import com.recrutai.api.institution.member.MemberService;
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
    private final InstitutionService institutionService;

    public VacancyService(
            VacancyRepository vacancyRepository,
            VacancyMapper vacancyMapper,
            MemberService memberService,
            InstitutionService institutionService) {
        this.vacancyRepository = vacancyRepository;
        this.vacancyMapper = vacancyMapper;
        this.memberService = memberService;
        this.institutionService = institutionService;
    }

    @Transactional
    public VacancyResponse create(VacancyRequest request) {
        var institution = institutionService.findById(request.getInstitutionId());
        var publisher = memberService.findById(request.getPublishedById());
        var recruiter = memberService.findById(request.getRecruiterId());

        var vacancy = vacancyMapper.mapToEntity(request, institution, recruiter, publisher);
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
            Long institutionId,
            WorkModel workModel,
            EmploymentType employmentType
    ) {
        var title = opTitle.map(String::strip).orElse("");
        return vacancyRepository.search(title, locationId, institutionId, workModel, employmentType);
    }

}
