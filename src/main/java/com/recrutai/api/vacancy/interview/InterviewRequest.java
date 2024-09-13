package com.recrutai.api.vacancy.interview;

import com.recrutai.api.address.AddressRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record InterviewRequest(
        @NotNull
        @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
        Long interviewerId,

        @NotNull
        @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
        Long applicationId,

        @NotEmpty
        @Size(max = 100)
        @Schema(example = "Interview with Tech Lead", requiredMode = RequiredMode.REQUIRED)
        String title,

        @Size(max = 2000)
        @Schema(
                example = "In this interview, we'll test your technical knowledge and check if you match the requirements.",
                requiredMode = RequiredMode.NOT_REQUIRED
        )
        String description,

        @NotNull
        @Future
        OffsetDateTime scheduledTo,

        @Valid
        @Schema(implementation = AddressRequest.class, requiredMode = RequiredMode.NOT_REQUIRED)
        AddressRequest address,

        @Size(max = 255)
        @Schema(example = "https://meet.google.com/xxx-yyy-zzz", requiredMode = RequiredMode.NOT_REQUIRED)
        String reunionUrl,

        @NotNull
        @Schema(example = "true", requiredMode = RequiredMode.REQUIRED)
        Boolean isRemote,

        @NotNull
        @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
        Long createdById
) {
}
