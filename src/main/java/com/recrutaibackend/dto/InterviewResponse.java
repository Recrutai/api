package com.recrutaibackend.dto;

import java.time.OffsetDateTime;

public record InterviewResponse(
        long id,
        ApplicationResponse application,
        MemberResponse interviewer,
        String title,
        String description,
        OffsetDateTime scheduledTo,
        AddressResponse address,
        String reunionURL,
        boolean isRemote,
        MemberResponse createdBy
) {
}
