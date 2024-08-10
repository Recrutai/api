package com.recrutaibackend.service;

import com.recrutaibackend.dto.VacancyRequest;
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

    public Vacancy create(VacancyRequest request) {
        var publisher = memberService.findById(request.publisherId());
        var recruiter = memberService.findById(request.recruiterId());

        var vacancy = vacancyMapper.mapToEntity(request, recruiter, publisher);

        return vacancyRepository.save(vacancy);
    }

    public Vacancy findById(Integer id) {
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vacancy not found"));
    }

    public Vacancy findByTitle(String title) {
        return vacancyRepository.findByTitle(title)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vacancy not found"));
    }

    public List<Vacancy> findAll() {
        return vacancyRepository.findAll();
    }

}
