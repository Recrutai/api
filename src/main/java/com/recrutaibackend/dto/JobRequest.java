package com.recrutaibackend.dto;

import com.recrutaibackend.model.ModalityJob;
import com.recrutaibackend.model.TypeJob;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record JobRequest(
        @NotNull
        Integer userId,
        @NotEmpty
        @Size(max = 40)
        String title,
        @NotNull
        TypeJob typeJob,
        @NotNull
        ModalityJob modalityJob,
        @NotEmpty
        @Size(max = 40)
        String companyName,
        @NotEmpty
        @Size(max = 40)
        String city,
        @NotNull
        Date dateStart,
        Date dateEnd,
        @NotNull
        Boolean currentJob
) {
}
