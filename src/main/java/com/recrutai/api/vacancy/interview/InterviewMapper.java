package com.recrutai.api.vacancy.interview;

import com.recrutai.api.address.AddressMapper;
import com.recrutai.api.auth.user.UserMapper;
import com.recrutai.api.organization.member.Member;
import com.recrutai.api.vacancy.application.Application;
import org.springframework.stereotype.Service;

@Service
public class InterviewMapper {
    private final AddressMapper addressMapper;
    private final UserMapper userMapper;

    public InterviewMapper(AddressMapper addressMapper, UserMapper userMapper) {
        this.addressMapper = addressMapper;
        this.userMapper = userMapper;
    }

    public Interview mapToEntity(
            InterviewRequest request,
            Application application,
            Member interviewer,
            Member createdBy
    ) {
        return new Interview(
                application,
                interviewer,
                request.getTitle(),
                request.getDescription(),
                request.getScheduledTo(),
                addressMapper.mapToEntity(request.getAddress()),
                request.getReunionUrl(),
                request.getIsRemote(),
                createdBy
        );
    }

    public InterviewResponse mapToResponse(Interview entity) {
        return new InterviewResponse(
                entity.getId(),
                userMapper.mapToResponse(entity.getApplication().getCandidate()),
                userMapper.mapToResponse(entity.getInterviewer().getUser()),
                entity.getTitle(),
                entity.getDescription(),
                entity.getScheduledTo(),
                addressMapper.mapToResponse(entity.getAddress()),
                entity.getReunionURL(),
                entity.getIsRemote(),
                userMapper.mapToResponse(entity.getCreatedBy().getUser())
        );
    }

}
