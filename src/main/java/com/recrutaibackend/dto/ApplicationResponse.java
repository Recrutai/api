package com.recrutaibackend.dto;

public record ApplicationResponse(
        int id,
        int candidateId,
        int vacancyId,
        int expectedSalary
) {
}
