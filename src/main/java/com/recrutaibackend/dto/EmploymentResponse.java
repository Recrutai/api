package com.recrutaibackend.dto;

import java.time.LocalDate;

public record EmploymentResponse(
        long id,
        UserResponse user,
        InstitutionResponse institution,
        String title,
        String type,
        String workModel,
        AddressResponse workAddress,
        String description,
        LocalDate startDate,
        LocalDate endDate
) {
}
