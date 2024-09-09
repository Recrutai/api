package com.recrutaibackend.institution;

import com.recrutaibackend.address.CityAddressResponse;
import io.swagger.v3.oas.annotations.media.Schema;

public record SimpleInstitutionResponse(
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
