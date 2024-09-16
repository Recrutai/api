package com.recrutai.api.institution.member;

import com.recrutai.api.auth.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;

@Getter
public class MemberResponse {
    @Schema(implementation = UserResponse.class)
    private final UserResponse user;

    @Schema(example = "OWNER")
    private final MemberRole role;

    @Schema(implementation = UserResponse.class)
    private final UserResponse addedBy;

    private final Instant addedAt;

    public MemberResponse(UserResponse user, MemberRole role, UserResponse addedBy, Instant addedAt) {
        this.user = user;
        this.role = role;
        this.addedBy = addedBy;
        this.addedAt = addedAt;
    }

}
