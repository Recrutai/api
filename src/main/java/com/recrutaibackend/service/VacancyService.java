package com.recrutaibackend.service;

import com.recrutaibackend.dto.VacancyRequest;
import com.recrutaibackend.dto.VacancyResponse;
import com.recrutaibackend.repository.VacancyRepository;
import org.springframework.stereotype.Service;

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

    public VacancyResponse create(VacancyRequest request) {
        var publisher = memberService.findById(request.publisherId());
        var recruiter = memberService.findById(request.recruiterId());

        var vacancy = vacancyMapper.mapToEntity(request, recruiter, publisher);
        var savedVacancy = vacancyRepository.save(vacancy);

        return vacancyMapper.mapToResponse(savedVacancy);
    }

}
