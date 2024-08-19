package com.recrutaibackend.dto;

public record SchoolResponse(
        long id,
        InstitutionResponse institution,
        int schoolSizeId,
        int associatedAlumni
) {
}
