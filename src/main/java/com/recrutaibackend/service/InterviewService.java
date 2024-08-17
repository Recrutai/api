package com.recrutaibackend.service;

import com.recrutaibackend.dto.InterviewRequest;
import com.recrutaibackend.mappers.InterviewMapper;
import com.recrutaibackend.model.Application;
import com.recrutaibackend.model.Interview;
import com.recrutaibackend.model.InterviewModel;
import com.recrutaibackend.model.Member;
import com.recrutaibackend.repository.InterviewRepository;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final InterviewMapper interviewMapper;
    private final MemberService memberService;
    private final ApplicationService applicationService;
    private final AddressService addressService;

    public InterviewService(
            InterviewRepository interviewRepository,
            InterviewMapper interviewMapper,
            MemberService memberService,
            ApplicationService applicationService,
            AddressService addressService
    ) {
        this.interviewRepository = interviewRepository;
        this.interviewMapper = interviewMapper;
        this.memberService = memberService;
        this.applicationService = applicationService;
        this.addressService = addressService;
    }

    public Interview create(InterviewRequest request) {
        var interviewer = memberService.findById(request.interviewerId());
        var application = applicationService.findById(request.candidateApplicationId());
        var creator = memberService.findById(request.createdBy());
        if (request.model().equals(InterviewModel.LOCAL)) {
            return createLocal(request, interviewer, application, creator);
        }
        return createRemote(request, interviewer, application, creator);
    }

    private Interview createLocal(
            InterviewRequest request,
            Member interviewer,
            Application application,
            Member creator
    ) {
        var address = addressService.create(request.address());
        var interview = interviewMapper.mapToLocalEntity(request, interviewer, application, address, creator);
        return interviewRepository.save(interview);
    }

    private Interview createRemote(
            InterviewRequest request,
            Member interviewer,
            Application application,
            Member creator
    ) {
        var interview = interviewMapper.mapToRemoteEntity(request, interviewer, application, creator);
        return interviewRepository.save(interview);
    }
}
