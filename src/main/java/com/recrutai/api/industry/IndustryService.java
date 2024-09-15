package com.recrutai.api.industry;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class IndustryService {
    private final IndustryRepository industryRepository;

    public IndustryService(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    public Industry findByName(String name) {
        return industryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Industry not found"));
    }

    public List<String> findAllFiltered(Optional<String> name) {
        return industryRepository.findAllFiltered(name.map(String::strip).orElse(""));
    }

}
