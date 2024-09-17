package com.recrutai.api.organization;

import com.recrutai.api.address.AddressResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;

@Getter
public class OrganizationResponse {
    @Schema(example = "1")
    private final long id;

    @Schema(example = "University of Oxford")
    private final String name;

    @Schema(example = "PUBLIC")
    private final String type;

    @Schema(example = "The official University of Oxford Recrutaí page.")
    private final String headline;

    @Schema(example = "Education")
    private final String industry;

    @Schema(example = "10.001+")
    private final String companySize;

    @Schema(example = "27450")
    private final int associatedEmployees;

    @Schema(implementation = AddressResponse.class)
    private final AddressResponse headquarters;

    @Schema(example = "www.ox.ac.uk")
    private final String website;

    @Schema(example = "Ranked number one in the world in the 2023 Times Higher Education World Rankings, we are at the forefront of the full range of academic disciplines, including medical sciences; mathematical, physical and life sciences; humanities; and social sciences. As the oldest university in the English-speaking world, we have long traditions of scholarship, but we are also forward-looking, creative and cutting-edge. Oxford is one of Europe's most entrepreneurial universities: we rank first in the UK for university spin-outs, with more than 130 companies created to date. We are also recognised as leaders in support for social enterprise.")
    private final String about;

    private final Instant createdAt;

    public OrganizationResponse(
            long id,
            String name,
            String type,
            String headline,
            String industry,
            String companySize,
            int associatedEmployees,
            AddressResponse headquarters,
            String website,
            String about,
            Instant createdAt
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.headline = headline;
        this.industry = industry;
        this.companySize = companySize;
        this.associatedEmployees = associatedEmployees;
        this.headquarters = headquarters;
        this.website = website;
        this.about = about;
        this.createdAt = createdAt;
    }

}
