package com.recrutaibackend.dto;

import java.time.Instant;

public record VacancyResponse(
        long id,
        String title,
        String description,
        String workModel,
        AddressResponse location,
        int salary,
        String currencyCode,
        short positions,
        int applications,
        MemberResponse recruiter,
        MemberResponse publishedBy,
        Instant publishedAt,
        MemberResponse closedBy,
        Instant closedAt
) {
}
