package com.recrutai.api.institution.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;

@Getter
public class MemberResponse {
    @Schema(example = "1")
    private final long id;

    @Schema(example = "1")
    private final long userId;

    @Schema(example = "1")
    private final long institutionId;

    @Schema(example = "OWNER")
    private final String role;

    @Schema(example = "2")
    private final Long addedById;

    private final Instant addedAt;

    @Schema(example = "2")
    private final Long removedById;

    private final Instant removedAt;

    public MemberResponse(
            long id,
            long userId,
            long institutionId,
            String role,
            Long addedById,
            Instant addedAt,
            Long removedById,
            Instant removedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.institutionId = institutionId;
        this.role = role;
        this.addedById = addedById;
        this.addedAt = addedAt;
        this.removedById = removedById;
        this.removedAt = removedAt;
    }

}
