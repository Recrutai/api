package com.recrutaibackend.vacancy.interview;

import com.recrutaibackend.address.AddressRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record InterviewRequest(
        @NotNull
        Long interviewerId,

        @NotNull
        Long applicationId,

        @NotEmpty
        @Size(max = 100)
        String title,

        @Size(max = 2000)
        String description,

        @NotNull
        OffsetDateTime scheduledTo,

        @Valid
        AddressRequest address,

        @Size(max = 255)
        String reunionUrl,

        @NotNull
        Boolean isRemote,

        @NotNull
        Long createdBy
) {
}
