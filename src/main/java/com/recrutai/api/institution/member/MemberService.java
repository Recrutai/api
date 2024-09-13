package com.recrutai.api.institution.member;

import com.recrutai.api.auth.user.UserService;
import com.recrutai.api.institution.InstitutionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final UserService userService;
    private final MemberMapper memberMapper;
    private final InstitutionService institutionService;

    public MemberService(
            MemberRepository memberRepository,
            UserService userService,
            MemberMapper memberMapper,
            InstitutionService institutionService
    ) {
        this.memberRepository = memberRepository;
        this.userService = userService;
        this.memberMapper = memberMapper;
        this.institutionService = institutionService;
    }

    @Transactional
    public MemberResponse create(long institutionId, MemberRequest request) {
        var institution = institutionService.findById(institutionId);
        var user = userService.findById(request.userId());
        var addedBy = findById(request.addedById());

        var member = memberMapper.mapToEntity(request, user, institution, addedBy);
        var savedMember = memberRepository.save(member);

        return memberMapper.mapToResponse(savedMember);
    }

    public List<MemberResponse> findAllByInstitutionId(long id) {
        return memberRepository.findAllByInstitutionId(id)
                .stream()
                .map(memberMapper::mapToResponse)
                .toList();
    }

    public Member findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Member not found"));
    }

}
