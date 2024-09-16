package com.recrutai.api.vacancy.interview;

import com.recrutai.api.address.AddressRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class InterviewRequest {
    @NotNull
    @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
    private final Long interviewerId;

    @NotNull
    @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
    private final Long applicationId;

    @NotEmpty
    @Size(max = 100)
    @Schema(example = "Interview with Tech Lead", requiredMode = RequiredMode.REQUIRED)
    private final String title;

    @Size(max = 2000)
    @Schema(
            example = "In this interview, we'll test your technical knowledge and check if you match the requirements.",
            requiredMode = RequiredMode.NOT_REQUIRED
    )
    private final String description;

    @NotNull
    @Future
    private final OffsetDateTime scheduledTo;

    @Valid
    @Schema(implementation = AddressRequest.class, requiredMode = RequiredMode.NOT_REQUIRED)
    private final AddressRequest address;

    @Size(max = 255)
    @Schema(example = "https://meet.google.com/xxx-yyy-zzz", requiredMode = RequiredMode.NOT_REQUIRED)
    private final String reunionUrl;

    @NotNull
    @Schema(example = "true", requiredMode = RequiredMode.REQUIRED)
    private final Boolean isRemote;

    @NotNull
    @Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
    private final Long createdById;

    public InterviewRequest(
            Long interviewerId,
            Long applicationId,
            String title,
            String description,
            OffsetDateTime scheduledTo,
            AddressRequest address,
            String reunionUrl,
            Boolean isRemote,
            Long createdById
    ) {
        this.interviewerId = interviewerId;
        this.applicationId = applicationId;
        this.title = title;
        this.description = description;
        this.scheduledTo = scheduledTo;
        this.address = address;
        this.reunionUrl = reunionUrl;
        this.isRemote = isRemote;
        this.createdById = createdById;
    }

}
