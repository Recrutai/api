package com.recrutaibackend.profile.employment;

import com.recrutaibackend.address.AddressResponse;
import com.recrutaibackend.institution.InstitutionResponse;
import com.recrutaibackend.auth.user.UserResponse;

import java.time.LocalDate;

public record EmploymentResponse(
        long id,
        UserResponse user,
        InstitutionResponse institution,
        String title,
        String type,
        String workModel,
        String description,
        LocalDate startDate,
        LocalDate endDate
) {
}
