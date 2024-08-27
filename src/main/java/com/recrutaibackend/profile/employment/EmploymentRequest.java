package com.recrutaibackend.profile.employment;

import com.recrutaibackend.address.AddressRequest;
import com.recrutaibackend.shared.WorkModel;
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

        @Size(max = 2500)
        String description,

        @NotNull
        LocalDate startDate,

        LocalDate endDate
) {
}
