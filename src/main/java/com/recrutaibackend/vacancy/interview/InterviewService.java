package com.recrutaibackend.vacancy.interview;

import com.recrutaibackend.institution.member.MemberService;
import com.recrutaibackend.vacancy.application.ApplicationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {
    private final InterviewRepository interviewRepository;
    private final InterviewMapper interviewMapper;
    private final MemberService memberService;
    private final ApplicationService applicationService;

    public InterviewService(
            InterviewRepository interviewRepository,
            InterviewMapper interviewMapper,
            MemberService memberService,
            ApplicationService applicationService
    ) {
        this.interviewRepository = interviewRepository;
        this.interviewMapper = interviewMapper;
        this.memberService = memberService;
        this.applicationService = applicationService;
    }

    @Transactional
    public Interview create(InterviewRequest request) {
        var application = applicationService.findById(request.applicationId());
        var interviewer = memberService.findById(request.interviewerId());
        var createdBy = memberService.findById(request.createdById());

        var interview = interviewMapper.mapToEntity(request, application, interviewer, createdBy);

        return interviewRepository.save(interview);
    }

}
