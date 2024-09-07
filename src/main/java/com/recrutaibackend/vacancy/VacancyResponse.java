package com.recrutaibackend.vacancy;

import com.recrutaibackend.address.AddressResponse;

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
        long recruiterId,
        long publishedById,
        Instant publishedAt,
        Long closedById,
        Instant closedAt
) {
}
