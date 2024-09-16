package com.recrutai.api.vacancy.interview;

import com.recrutai.api.address.AddressRequest;
import com.recrutai.api.address.AddressResponse;
import com.recrutai.api.auth.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class InterviewResponse {
    @Schema(example = "1")
    private final long id;

    @Schema(implementation = UserResponse.class)
    private final UserResponse candidate;

    @Schema(implementation = UserResponse.class)
    private final UserResponse interviewer;

    @Schema(example = "Interview with Tech Lead")
    private final String title;

    @Schema(example = "In this interview, we'll test your technical knowledge and check if you match the requirements.")
    private final String description;

    private final OffsetDateTime scheduledTo;

    @Schema(implementation = AddressRequest.class)
    private final AddressResponse address;

    @Schema(example = "https://meet.google.com/xxx-yyy-zzz")
    private final String reunionURL;

    @Schema(example = "false")
    private final boolean isRemote;

    @Schema(implementation = UserResponse.class)
    private final UserResponse createdBy;

    public InterviewResponse(
            long id,
            UserResponse candidate,
            UserResponse interviewer,
            String title,
            String description,
            OffsetDateTime scheduledTo,
            AddressResponse address,
            String reunionURL,
            boolean isRemote,
            UserResponse createdBy
    ) {
        this.id = id;
        this.candidate = candidate;
        this.interviewer = interviewer;
        this.title = title;
        this.description = description;
        this.scheduledTo = scheduledTo;
        this.address = address;
        this.reunionURL = reunionURL;
        this.isRemote = isRemote;
        this.createdBy = createdBy;
    }

}
