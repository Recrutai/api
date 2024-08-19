package com.recrutaibackend.mappers;

import com.recrutaibackend.dto.MemberRequest;
import com.recrutaibackend.dto.MemberResponse;
import com.recrutaibackend.model.Institution;
import com.recrutaibackend.model.Member;
import com.recrutaibackend.model.User;
import org.springframework.stereotype.Service;

@Service
public class MemberMapper {

    private final UserMapper userMapper;
    private final InstitutionMapper institutionMapper;

    public MemberMapper(UserMapper userMapper, InstitutionMapper institutionMapper) {
        this.userMapper = userMapper;
        this.institutionMapper = institutionMapper;
    }

    public Member mapToEntity(MemberRequest request, User user, Institution institution, Member addedBy) {
        return new Member(
                user,
                institution,
                request.role(),
                addedBy
        );
    }

    public MemberResponse mapToResponse(Member member) {
        return new MemberResponse(
                member.getId(),
                userMapper.mapToResponse(member.getUser()),
                institutionMapper.mapToResponse(member.getInstitution()),
                member.getRole(),
                member.getAddedBy().getId(),
                member.getAddedAt(),
                member.getRemovedBy().getId(),
                member.getRemovedAt()
        );
    }

}
