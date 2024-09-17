package com.recrutai.api.organization.member;

import com.recrutai.api.auth.user.UserService;
import com.recrutai.api.organization.OrganizationService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final UserService userService;
    private final MemberMapper memberMapper;
    private final OrganizationService organizationService;

    public MemberService(
            MemberRepository memberRepository,
            UserService userService,
            MemberMapper memberMapper,
            OrganizationService organizationService
    ) {
        this.memberRepository = memberRepository;
        this.userService = userService;
        this.memberMapper = memberMapper;
        this.organizationService = organizationService;
    }

    @Transactional
    public MemberResponse create(long organizationId, MemberRequest request) {
        if (memberRepository.exists(organizationId, request.getUserId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User is already a member of the organization");
        }

        var organization = organizationService.findById(organizationId);
        var user = userService.findById(request.getUserId());
        var addedBy = findById(request.getAddedById());

        var member = memberMapper.mapToEntity(request, user, organization, addedBy);
        var savedMember = memberRepository.save(member);

        return memberMapper.mapToResponse(savedMember);
    }

    public List<MemberResponse> findAllByOrganizationId(long id) {
        return memberRepository.findAllByOrganizationId(id)
                .stream()
                .map(memberMapper::mapToResponse)
                .toList();
    }

    public Member findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Member not found"));
    }

}
