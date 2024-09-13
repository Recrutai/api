package com.recrutai.api.institution;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.recrutai.api.address.CityAddressResponse;
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

    @Schema(implementation = CityAddressResponse.class)
    private final CityAddressResponse headquarters;

    public InstitutionSummaryResponse(long id, String name, String headline, CityAddressResponse headquarters) {
        this.id = id;
        this.name = name;
        this.headline = headline;
        this.headquarters = headquarters;
    }

}
