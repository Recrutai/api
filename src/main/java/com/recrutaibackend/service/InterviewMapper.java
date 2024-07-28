package com.recrutaibackend.service;

import com.recrutaibackend.dto.InterviewRequest;
import com.recrutaibackend.dto.InterviewResponse;
import com.recrutaibackend.model.Address;
import com.recrutaibackend.model.Application;
import com.recrutaibackend.model.Interview;
import com.recrutaibackend.model.Member;
import org.springframework.stereotype.Service;

@Service
public class InterviewMapper {

    private final AddressMapper addressMapper;

    public InterviewMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public Interview mapToRemoteEntity(InterviewRequest request, Member interviewer, Application application, Member creator) {
        return Interview.createRemote(
                interviewer,
                application,
                request.title(),
                request.description(),
                request.scheduledTo(),
                request.reunionUrl(),
                creator
        );
    }

    public Interview mapToLocalEntity(InterviewRequest request, Member interviewer, Application application, Address address, Member creator) {
        return Interview.createLocal(
                interviewer,
                application,
                request.title(),
                request.description(),
                request.scheduledTo(),
                address,
                creator
        );
    }

    public InterviewResponse mapToResponse(Interview entity) {
        return new InterviewResponse(
                entity.getId(),
                entity.getInterviewer().getId(),
                entity.getCandidateApplication().getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getScheduledTo(),
                entity.getModel().toString(),
                entity.getReunionURL(),
                entity.getAddress() != null ? addressMapper.mapToResponse(entity.getAddress()) : null,
                entity.getCreatedBy().getId()
        );
    }
}
