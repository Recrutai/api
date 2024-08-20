package com.recrutaibackend.service;

import com.recrutaibackend.dto.MemberRequest;
import com.recrutaibackend.dto.MemberResponse;
import com.recrutaibackend.mappers.MemberMapper;
import com.recrutaibackend.model.Institution;
import com.recrutaibackend.model.Member;
import com.recrutaibackend.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final UserService userService;
    private final MemberMapper memberMapper;

    public MemberService(
            MemberRepository memberRepository,
            UserService userService,
            MemberMapper memberMapper
    ) {
        this.memberRepository = memberRepository;
        this.userService = userService;
        this.memberMapper = memberMapper;
    }

    @Transactional
    public MemberResponse create(Institution institution, MemberRequest request) {
        var user = userService.findById(request.userId());
        var addedBy = this.findById(request.addedById());

        var member = memberMapper.mapToEntity(request, user, institution, addedBy);
        var savedMember = memberRepository.save(member);

        return memberMapper.mapToResponse(savedMember);
    }

    public List<MemberResponse> findAllByInstitutionId(long institutionId) {
        return memberRepository.findAllByInstitutionId(institutionId)
                .stream()
                .map(memberMapper::mapToResponse)
                .toList();
    }

    public Member findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    }
}
