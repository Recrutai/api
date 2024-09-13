package com.recrutai.api.vacancy.interview;

import com.recrutai.api.address.AddressRequest;
import com.recrutai.api.address.AddressResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;

public record InterviewResponse(
        @Schema(example = "1")
        long id,

        @Schema(example = "1")
        long applicationId,

        @Schema(example = "1")
        long interviewerId,

        @Schema(example = "Interview with Tech Lead")
        String title,

        @Schema(example = "In this interview, we'll test your technical knowledge and check if you match the requirements.")
        String description,

        OffsetDateTime scheduledTo,

        @Schema(implementation = AddressRequest.class)
        AddressResponse address,

        @Schema(example = "https://meet.google.com/xxx-yyy-zzz")
        String reunionURL,

        @Schema(example = "false")
        boolean isRemote,

        @Schema(example = "1")
        long createdById
) {
}
