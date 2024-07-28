package com.recrutaibackend.dto;

public record VacancyResponse(
        int id,
        String title,
        String description,
        String workModel,
        double avgSalary,
        short positions,
        int applications,
        int recruiterId,
        int publisherId
) {
}
