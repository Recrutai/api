package com.recrutaibackend.dto;

import java.time.Instant;

public record ApplicationResponse(
        long id,
        UserResponse user,
        VacancyResponse vacancy,
        int expectedSalary,
        String currencyCode,
        Instant appliedAt
) {
}
