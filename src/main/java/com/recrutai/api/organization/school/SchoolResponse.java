package com.recrutai.api.organization.school;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.recrutai.api.organization.OrganizationResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SchoolResponse {
    @JsonUnwrapped
    private final OrganizationResponse organization;

    @Schema(example = "10.001+")
    private final String schoolSize;

    @Schema(example = "12580")
    private final int associatedAlumni;

    public SchoolResponse(OrganizationResponse organization, String schoolSize, int associatedAlumni) {
        this.organization = organization;
        this.schoolSize = schoolSize;
        this.associatedAlumni = associatedAlumni;
    }

}
