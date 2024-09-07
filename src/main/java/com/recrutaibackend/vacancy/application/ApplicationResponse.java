package com.recrutaibackend.vacancy.application;

import java.time.Instant;

public record ApplicationResponse(
        long id,
        long userId,
        int expectedSalary,
        String currencyCode,
        Instant appliedAt
) {
}
