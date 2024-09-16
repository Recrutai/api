package com.recrutai.api.institution.member;

import com.recrutai.api.auth.user.User;
import com.recrutai.api.auth.user.UserMapper;
import com.recrutai.api.institution.Institution;
import org.springframework.stereotype.Service;

@Service
public class MemberMapper {
    private final UserMapper userMapper;

    public MemberMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Member mapToEntity(MemberRequest request, User user, Institution institution, Member addedBy) {
        return new Member(
                user,
                institution,
                request.getRole(),
                addedBy
        );
    }

    public MemberResponse mapToResponse(Member entity) {
        var addedBy = entity.getAddedBy() != null ? entity.getAddedBy().getUser() : null;
        return new MemberResponse(
                userMapper.mapToResponse(entity.getUser()),
                entity.getRole(),
                userMapper.mapToResponse(addedBy),
                entity.getAddedAt()
        );
    }

}
