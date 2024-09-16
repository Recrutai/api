package com.recrutai.api.institution;

import com.recrutai.api.address.AddressRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class InstitutionRequest {
    @NotEmpty
    @Size(max = 120)
    @Schema(example = "University of Oxford", requiredMode = RequiredMode.REQUIRED)
    private final String name;

    @NotNull
    @Schema(example = "PUBLIC", requiredMode = RequiredMode.REQUIRED)
    private final InstitutionType type;

    @Size(max = 150)
    @Schema(example = "The official University of Oxford Recrutaí page.", requiredMode = RequiredMode.REQUIRED)
    private final String headline;

    @NotNull
    @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
    private final Long founderId;

    @NotEmpty
    @Size(max = 100)
    @Schema(example = "Education", requiredMode = RequiredMode.REQUIRED)
    private final String industry;

    @NotNull
    @Schema(example = "10.001+", requiredMode = RequiredMode.REQUIRED)
    private final InstitutionSize companySize;

    @Valid
    @Schema(implementation = AddressRequest.class, requiredMode = RequiredMode.NOT_REQUIRED)
    private final AddressRequest headquarters;

    @Size(max = 255)
    @Schema(example = "www.ox.ac.uk", requiredMode = RequiredMode.NOT_REQUIRED)
    private final String website;

    @Size(max = 2500)
    @Schema(
            example = "Ranked number one in the world in the 2023 Times Higher Education World Rankings, we are at the forefront of the full range of academic disciplines, including medical sciences; mathematical, physical and life sciences; humanities; and social sciences. As the oldest university in the English-speaking world, we have long traditions of scholarship, but we are also forward-looking, creative and cutting-edge. Oxford is one of Europe's most entrepreneurial universities: we rank first in the UK for university spin-outs, with more than 130 companies created to date. We are also recognised as leaders in support for social enterprise.",
            requiredMode = RequiredMode.NOT_REQUIRED
    )
    private final String about;

    public InstitutionRequest(
            String name,
            InstitutionType type,
            String headline,
            Long founderId,
            String industry,
            InstitutionSize companySize,
            AddressRequest headquarters,
            String website,
            String about
    ) {
        this.name = name;
        this.type = type;
        this.headline = headline;
        this.founderId = founderId;
        this.industry = industry;
        this.companySize = companySize;
        this.headquarters = headquarters;
        this.website = website;
        this.about = about;
    }

}
