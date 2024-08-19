package com.recrutaibackend.service;

import com.recrutaibackend.dto.MemberRequest;
import com.recrutaibackend.mappers.MemberMapper;
import com.recrutaibackend.model.Member;
import com.recrutaibackend.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final UserService userService;
    private final InstitutionService institutionService;
    private final MemberMapper memberMapper;

    public MemberService(
            MemberRepository memberRepository,
            UserService userService,
            InstitutionService institutionService,
            MemberMapper memberMapper
    ) {
        this.memberRepository = memberRepository;
        this.userService = userService;
        this.institutionService = institutionService;
        this.memberMapper = memberMapper;
    }

    @Transactional
    public Member create(MemberRequest request) {
        var user = userService.findById(request.userId());
        var institution = institutionService.findById(request.institutionId());
        var addedBy = this.findById(request.addedById());

        var member = memberMapper.mapToEntity(request, user, institution, addedBy);

        return memberRepository.save(member);
    }

    public Member findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    }
}
