package com.recrutai.api.institution.school;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.recrutai.api.institution.InstitutionRequest;
import com.recrutai.api.institution.InstitutionSize;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SchoolRequest {
    @NotNull
    @Valid
    @JsonUnwrapped
    private final InstitutionRequest institution;

    @NotNull
    @Schema(example = "10.001+", requiredMode = RequiredMode.REQUIRED)
    private final InstitutionSize schoolSize;

    public SchoolRequest(InstitutionRequest institution, InstitutionSize schoolSize) {
        this.institution = institution;
        this.schoolSize = schoolSize;
    }

}
