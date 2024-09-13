package com.recrutai.api.institution;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.recrutai.api.address.CityAddressResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(Include.NON_NULL)
public record InstitutionSummaryResponse(
        @Schema(example = "1")
        long id,

        @Schema(example = "University of Oxford")
        String name,

        @Schema(example = "The official University of Oxford Recrutaí page.")
        String headline,

        @Schema(implementation = CityAddressResponse.class)
        CityAddressResponse headquarters
) {
}
