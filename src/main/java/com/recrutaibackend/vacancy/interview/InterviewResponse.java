package com.recrutaibackend.vacancy.interview;

import com.recrutaibackend.address.AddressResponse;
import com.recrutaibackend.vacancy.application.ApplicationResponse;
import com.recrutaibackend.institution.member.MemberResponse;

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
