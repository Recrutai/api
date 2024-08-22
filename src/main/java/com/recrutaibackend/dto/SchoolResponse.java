package com.recrutaibackend.dto;

public record SchoolResponse(
        long id,
        InstitutionResponse institution,
        short schoolSizeId,
        int associatedAlumni
) {
}
