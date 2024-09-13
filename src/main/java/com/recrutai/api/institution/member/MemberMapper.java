package com.recrutai.api.institution.member;

import com.recrutai.api.auth.user.User;
import com.recrutai.api.institution.Institution;
import org.springframework.stereotype.Service;

@Service
public class MemberMapper {

    public Member mapToEntity(MemberRequest request, User user, Institution institution, Member addedBy) {
        return new Member(
                user,
                institution,
                request.getRole(),
                addedBy
        );
    }

    public MemberResponse mapToResponse(Member entity) {
        return new MemberResponse(
                entity.getId(),
                entity.getUser().getId(),
                entity.getInstitution().getId(),
                entity.getRole().toString(),
                entity.getAddedBy() != null ? entity.getAddedBy().getId() : null,
                entity.getAddedAt(),
                entity.getRemovedBy() != null ? entity.getRemovedBy().getId() : null,
                entity.getRemovedAt()
        );
    }

}
