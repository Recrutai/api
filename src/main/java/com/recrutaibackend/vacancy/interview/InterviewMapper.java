package com.recrutaibackend.vacancy.interview;

import com.recrutaibackend.address.AddressMapper;
import com.recrutaibackend.institution.member.Member;
import com.recrutaibackend.vacancy.application.Application;
import org.springframework.stereotype.Service;

@Service
public class InterviewMapper {
    private final AddressMapper addressMapper;

    public InterviewMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
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
                request.title(),
                request.description(),
                request.scheduledTo(),
                addressMapper.mapToEntity(request.address()),
                request.reunionUrl(),
                request.isRemote(),
                createdBy
        );
    }

    public InterviewResponse mapToResponse(Interview entity) {
        return new InterviewResponse(
                entity.getId(),
                entity.getApplication().getId(),
                entity.getInterviewer().getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getScheduledTo(),
                addressMapper.mapToResponse(entity.getAddress()),
                entity.getReunionURL(),
                entity.getIsRemote(),
                entity.getCreatedBy().getId()
        );
    }

}
