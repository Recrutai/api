package com.recrutai.api.organization.school;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.recrutai.api.organization.OrganizationRequest;
import com.recrutai.api.organization.OrganizationSize;
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
    private final OrganizationRequest organization;

    @NotNull
    @Schema(example = "10.001+", requiredMode = RequiredMode.REQUIRED)
    private final OrganizationSize schoolSize;

    public SchoolRequest(OrganizationRequest organization, OrganizationSize schoolSize) {
        this.organization = organization;
        this.schoolSize = schoolSize;
    }

}
