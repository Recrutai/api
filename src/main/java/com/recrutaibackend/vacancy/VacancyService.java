package com.recrutaibackend.vacancy;

import com.recrutaibackend.institution.member.MemberService;
import com.recrutaibackend.shared.EmploymentType;
import com.recrutaibackend.shared.WorkModel;
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

    public VacancyService(
            VacancyRepository vacancyRepository,
            VacancyMapper vacancyMapper,
            MemberService memberService
    ) {
        this.vacancyRepository = vacancyRepository;
        this.vacancyMapper = vacancyMapper;
        this.memberService = memberService;
    }

    @Transactional
    public VacancyResponse create(VacancyRequest request) {
        var publisher = memberService.findById(request.publishedById());
        var recruiter = memberService.findById(request.recruiterId());

        var vacancy = vacancyMapper.mapToEntity(request, recruiter, publisher);
        var savedVacancy = vacancyRepository.save(vacancy);

        return vacancyMapper.mapToResponse(savedVacancy);
    }

    public Vacancy findById(long id) {
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vacancy not found"));
    }

    public List<VacancyResponse> findAllByInstitutionId(long id) {
        return vacancyRepository.findAllByPublishedBy_Institution_Id(id)
                .stream()
                .map(vacancyMapper::mapToResponse)
                .toList();
    }

    public List<VacancySummaryResponse> findAllFiltered(
            Optional<String> title,
            Long locationId,
            WorkModel workModel,
            EmploymentType employmentType
    ) {
        return vacancyRepository.findAllFiltered(
                title.map(String::strip).orElse(""),
                locationId,
                workModel,
                employmentType
        );
    }

}
