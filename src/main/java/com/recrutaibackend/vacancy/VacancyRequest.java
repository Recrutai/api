package com.recrutaibackend.vacancy;

import com.recrutaibackend.address.AddressRequest;
import com.recrutaibackend.shared.WorkModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public record VacancyRequest(
        @NotEmpty
        @Size(max = 100)
        String title,

        @NotEmpty
        @Size(max = 3850)
        String description,

        @NotNull
        WorkModel workModel,

        @Valid
        AddressRequest location,

        @NotNull
        @Range(min = 1, max = 1_000_000)
        Integer salary,

        @NotNull
        @Range(min = 1, max = 500)
        Short positions,

        @NotNull
        Long recruiterId,

        @NotNull
        Long publishedById
) {
}
