package com.recrutaibackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record InterviewResponse(
        Integer id,
        Integer interviewerId,
        Integer candidateApplicationId,
        String title,
        String description,
        OffsetDateTime scheduledTo,
        String model,
        String reunionURL,
        AddressResponse address,
        Integer createdBy
) {
}
