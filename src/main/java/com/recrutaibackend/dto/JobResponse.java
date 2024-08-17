package com.recrutaibackend.dto;

import com.recrutaibackend.model.ModalityJob;
import com.recrutaibackend.model.TypeJob;
import java.util.Date;

public record JobResponse(
        String title,
        TypeJob typeJob,
        ModalityJob modalityJob,
        String companyName,
        String city,
        Date dateStart,
        Date dateEnd,
        Boolean currentJob
) {
}
