package com.recrutaibackend.dto;

public record ApplicationRequest(
        Integer candidateId,
        Integer vacancyId,
        Double expectedSalary
) {
}
