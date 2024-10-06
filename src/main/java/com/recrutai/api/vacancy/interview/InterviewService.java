package com.recrutai.api.vacancy.interview;

import com.recrutai.api.auth.user.UserService;
import com.recrutai.api.organization.member.MemberService;
import com.recrutai.api.vacancy.application.ApplicationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class InterviewService {
    private static final Duration REASONABLE_REMOTE_PREP_TIME = Duration.ofMinutes(30);
    private static final Duration REASONABLE_ON_SITE_PREP_TIME = Duration.ofHours(2);

    private final InterviewRepository interviewRepository;
    private final InterviewMapper interviewMapper;
    private final MemberService memberService;
    private final ApplicationService applicationService;
    private final UserService userService;

    public InterviewService(
            InterviewRepository interviewRepository,
            InterviewMapper interviewMapper,
            MemberService memberService,
            ApplicationService applicationService,
            UserService userService
    ) {
        this.interviewRepository = interviewRepository;
        this.interviewMapper = interviewMapper;
        this.memberService = memberService;
        this.applicationService = applicationService;
        this.userService = userService;
    }

    @Transactional
    public Interview create(InterviewRequest request) {
        validateInterviewSchedule(request);

        var application = applicationService.findById(request.getApplicationId());
        var interviewer = memberService.findById(request.getInterviewerId());
        var createdBy = memberService.findById(request.getCreatedById());

        var interview = interviewMapper.mapToEntity(request, application, interviewer, createdBy);

        return interviewRepository.save(interview);
    }

    private void validateInterviewSchedule(InterviewRequest request) {
        if (request.getIsRemote() && isPrepTimeNotEnough(request.getScheduledTo(), REASONABLE_REMOTE_PREP_TIME)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Remote interviews must be scheduled at least 30 minutes in advance");
        }
        if (!request.getIsRemote() && isPrepTimeNotEnough(request.getScheduledTo(), REASONABLE_ON_SITE_PREP_TIME)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "On-site interviews must be scheduled at least 2 hours in advance");
        }
    }

    private boolean isPrepTimeNotEnough(OffsetDateTime scheduledTo, Duration prepTime) {
        return scheduledTo.minus(prepTime).isBefore(OffsetDateTime.now());
    }

    public List<InterviewResponse> findAllByCandidate(long id) {
        var candidate = userService.findById(id);
        var interviews = interviewRepository.getAllByApplicationCandidate(candidate);
        return interviews.stream()
                .map(interviewMapper::mapToResponse)
                .toList();
    }

}
