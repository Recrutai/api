package com.recrutaibackend.institution.school;

import com.recrutaibackend.institution.InstitutionResponse;
import io.swagger.v3.oas.annotations.media.Schema;

public record SchoolResponse(
        @Schema(example = "1")
        long id,

        @Schema(implementation = InstitutionResponse.class)
        InstitutionResponse institution,

        @Schema(example = "10.001+")
        String schoolSize,

        @Schema(example = "12.580")
        int associatedAlumni
) {
}
