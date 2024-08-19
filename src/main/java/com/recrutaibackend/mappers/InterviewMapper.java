package com.recrutaibackend.mappers;

import com.recrutaibackend.dto.InterviewRequest;
import com.recrutaibackend.dto.InterviewResponse;
import com.recrutaibackend.model.Application;
import com.recrutaibackend.model.Interview;
import com.recrutaibackend.model.Member;
import org.springframework.stereotype.Service;

@Service
public class InterviewMapper {

    private final AddressMapper addressMapper;
    private final ApplicationMapper applicationMapper;
    private final MemberMapper memberMapper;

    public InterviewMapper(AddressMapper addressMapper, ApplicationMapper applicationMapper, MemberMapper memberMapper) {
        this.addressMapper = addressMapper;
        this.applicationMapper = applicationMapper;
        this.memberMapper = memberMapper;
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

    public InterviewResponse mapToResponse(Interview interview) {
        return new InterviewResponse(
                interview.getId(),
                applicationMapper.mapToResponse(interview.getApplication()),
                memberMapper.mapToResponse(interview.getInterviewer()),
                interview.getTitle(),
                interview.getDescription(),
                interview.getScheduledTo(),
                addressMapper.mapToResponse(interview.getAddress()),
                interview.getReunionURL(),
                interview.getIsRemote(),
                memberMapper.mapToResponse(interview.getCreatedBy())
        );
    }

}
