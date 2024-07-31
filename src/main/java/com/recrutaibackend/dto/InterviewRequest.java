package com.recrutaibackend.dto;

import com.recrutaibackend.model.InterviewModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record InterviewRequest(
        @NotNull
        Integer interviewerId,

        @NotNull
        Integer candidateApplicationId,

        @NotEmpty
        @Size(max = 120)
        String title,

        @Size(max = 2500)
        String description,

        @NotNull
        OffsetDateTime scheduledTo,

        @NotNull
        InterviewModel model,

        @Size(max = 2000)
        String reunionUrl,

        @Valid
        AddressRequest address,

        @NotNull
        Integer createdBy
) {
}
