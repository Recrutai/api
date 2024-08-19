package com.recrutaibackend.dto;

import com.recrutaibackend.model.WorkModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EmploymentRequest(
        @NotNull
        Long userId,

        @NotNull
        Long institutionId,

        @NotEmpty
        @Size(max = 100)
        String title,

        @Size(max = 50)
        String type,

        WorkModel workModel,

        @Valid
        AddressRequest workAddress,

        @Size(max = 2500)
        String description,

        @NotNull
        LocalDate startDate,

        LocalDate endDate
) {
}
