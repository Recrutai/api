package com.recrutaibackend.vacancy;

import com.recrutaibackend.institution.member.MemberService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    public List<VacancyResponse> findAllByTitle(String title) {
        return vacancyRepository.findAllWithLocationByTitleContainsIgnoreCase(title)
                .stream()
                .map(vacancyMapper::mapToResponse)
                .toList();
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
}
