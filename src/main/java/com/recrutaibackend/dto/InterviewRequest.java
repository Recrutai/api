package com.recrutaibackend.dto;

import com.recrutaibackend.model.InterviewModel;

import java.time.OffsetDateTime;

public record InterviewRequest(
        Integer interviewerId,
        Integer candidateApplicationId,
        String title,
        String description,
        OffsetDateTime scheduledTo,
        InterviewModel model,
        String reunionUrl,
        AddressRequest address,
        Integer createdBy
) {
}
