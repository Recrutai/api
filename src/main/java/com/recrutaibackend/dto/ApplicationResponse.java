package com.recrutaibackend.dto;

import com.recrutaibackend.model.User;
import com.recrutaibackend.model.Vacancy;

import java.time.Instant;

public record ApplicationResponse(
        int id,
        User candidate,
        Vacancy vacancy,
        Integer expectedSalary,
        String status,
        Instant appliedAt
) {
}
