package com.recrutaibackend.vacancy.application;

import com.recrutaibackend.auth.user.UserResponse;
import com.recrutaibackend.vacancy.VacancyResponse;

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
