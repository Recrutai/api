package com.recrutaibackend.profile.employment;

import com.recrutaibackend.address.CityAddressRequest;
import com.recrutaibackend.shared.WorkModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.YearMonth;

public record EmploymentRequest(
        @NotNull
        Long institutionId,

        @NotEmpty
        @Size(max = 100)
        String title,

        EmploymentType type,

        WorkModel workModel,

        @Valid
        CityAddressRequest address,

        @Size(max = 2500)
        String description,

        @NotNull
        YearMonth startDate,

        YearMonth endDate
) {
}
