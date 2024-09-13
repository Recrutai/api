package com.recrutai.api.institution.member;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberRequest {
    @NotNull
    @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private final Long userId;

    @NotNull
    @Schema(example = "OWNER", requiredMode = Schema.RequiredMode.REQUIRED)
    private final MemberRole role;

    @NotNull
    @Schema(example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private final Long addedById;

    public MemberRequest(Long userId, MemberRole role, Long addedById) {
        this.userId = userId;
        this.role = role;
        this.addedById = addedById;
    }

}
