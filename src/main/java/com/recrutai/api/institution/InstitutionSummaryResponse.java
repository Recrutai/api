package com.recrutai.api.institution;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class InstitutionSummaryResponse {
    @Schema(example = "1")
    private final long id;

    @Schema(example = "University of Oxford")
    private final String name;

    @Schema(example = "The official University of Oxford Recrutaí page.")
    private final String headline;

    @Schema(example = "Technology")
    private final String industry;

    @Schema(example = "Oxford, Oxfordshire")
    private final String headquarters;

    public InstitutionSummaryResponse(
            long id,
            String name,
            String headline,
            String industry,
            String headquarters
    ) {
        this.id = id;
        this.name = name;
        this.headline = headline;
        this.industry = industry;
        this.headquarters = headquarters;
    }

}
