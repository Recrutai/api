package com.recrutai.api.institution.school;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.recrutai.api.institution.InstitutionResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SchoolResponse {
    @JsonUnwrapped
    private final InstitutionResponse institution;

    @Schema(example = "10.001+")
    private final String schoolSize;

    @Schema(example = "12580")
    private final int associatedAlumni;

    public SchoolResponse(InstitutionResponse institution, String schoolSize, int associatedAlumni) {
        this.institution = institution;
        this.schoolSize = schoolSize;
        this.associatedAlumni = associatedAlumni;
    }

}
