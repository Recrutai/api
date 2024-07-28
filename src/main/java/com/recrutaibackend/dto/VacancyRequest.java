package com.recrutaibackend.dto;

public record VacancyRequest(
        String title,
        String description,
        String workModel,
        Double avgSalary,
        Short positions,
        Integer recruiterId,
        Integer publisherId
) {
}
