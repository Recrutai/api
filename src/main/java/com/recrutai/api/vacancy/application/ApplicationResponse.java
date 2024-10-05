package com.recrutai.api.vacancy.application;

import com.recrutai.api.auth.user.UserResponse;
import com.recrutai.api.organization.OrganizationResponse;
import com.recrutai.api.shared.CurrencyCode;
import com.recrutai.api.vacancy.VacancyResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;

@Getter
public class ApplicationResponse {
    @Schema(example = "1")
    private final long id;

    @Schema(implementation = UserResponse.class)
    private final UserResponse candidate;

    @Schema(implementation = VacancyResponse.class)
    private final VacancyResponse vacancy;

    @Schema(example = "5400")
    private final int expectedSalary;

    @Schema(example = "USD")
    private final CurrencyCode currencyCode;

    private final Instant appliedAt;

    public ApplicationResponse(
            long id,
            UserResponse candidate, VacancyResponse vacancy,
            int expectedSalary,
            CurrencyCode currencyCode,
            Instant appliedAt
    ) {
        this.id = id;
        this.candidate = candidate;
        this.vacancy = vacancy;
        this.expectedSalary = expectedSalary;
        this.currencyCode = currencyCode;
        this.appliedAt = appliedAt;
    }

}
