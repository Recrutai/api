package com.recrutai.api.vacancy.application;

import com.recrutai.api.shared.CurrencyCode;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class ApplicationRequest {
    @NotNull
    @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
    private final Long candidateId;

    @NotNull
    @Range(min = 1, max = 1_000_000)
    @Schema(example = "5400", requiredMode = RequiredMode.REQUIRED)
    private final Integer expectedSalary;

    @NotNull
    @Schema(example = "USD", requiredMode = RequiredMode.REQUIRED)
    private final CurrencyCode currency;

    public ApplicationRequest(Long candidateId, Integer expectedSalary, CurrencyCode currency) {
        this.candidateId = candidateId;
        this.expectedSalary = expectedSalary;
        this.currency = currency;
    }

}
