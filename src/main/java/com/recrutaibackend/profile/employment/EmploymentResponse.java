package com.recrutaibackend.profile.employment;

import com.recrutaibackend.address.AddressResponse;

import java.time.YearMonth;

public record EmploymentResponse(
        long id,
        long institutionId,
        String title,
        String type,
        String workModel,
        AddressResponse address,
        String description,
        YearMonth startDate,
        YearMonth endDate
) {
}
