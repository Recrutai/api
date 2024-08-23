package com.recrutaibackend.institution.school;

import com.recrutaibackend.institution.InstitutionResponse;

public record SchoolResponse(
        long id,
        InstitutionResponse institution,
        short schoolSizeId,
        int associatedAlumni
) {
}
