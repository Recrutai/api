package com.recrutaibackend.service;

import com.recrutaibackend.dto.VacancyRequest;
import com.recrutaibackend.dto.VacancyResponse;
import com.recrutaibackend.model.Vacancy;
import com.recrutaibackend.repository.VacancyRepository;
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

    public VacancyResponse create(VacancyRequest request) {
        var publisher = memberService.findById(request.publisherId());
        var recruiter = memberService.findById(request.recruiterId());

        var vacancy = vacancyMapper.mapToEntity(request, recruiter, publisher);
        var savedVacancy = vacancyRepository.save(vacancy);

        return vacancyMapper.mapToResponse(savedVacancy);
    }

    public Vacancy findVacancy(Integer id) {
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vacancy not found"));
    }

    public VacancyResponse vacancyDetails(Integer id) {
        var vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vacancy not found"));

        return vacancyMapper.mapToResponse(vacancy);
    }

    public VacancyResponse findVacancyByTitle(String title) {
        var vacancy = vacancyRepository.findByTitle(title)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vacancy not found"));

        return vacancyMapper.mapToResponse(vacancy);
    }

    public List<VacancyResponse> getAllVacancys() {
        var vacancys = vacancyRepository.findAll();
        return vacancyMapper.streamList(vacancys);
    }

}
