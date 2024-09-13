package com.recrutai.api.institution.school;

import com.recrutai.api.institution.InstitutionResponse;
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
