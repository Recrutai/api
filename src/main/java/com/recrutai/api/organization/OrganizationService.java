package com.recrutai.api.organization;

import com.recrutai.api.auth.user.User;
import com.recrutai.api.auth.user.UserService;
import com.recrutai.api.industry.IndustryService;
import com.recrutai.api.organization.member.Member;
import com.recrutai.api.organization.member.MemberRepository;
import com.recrutai.api.organization.member.MemberRole;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    private final UserService userService;
    private final IndustryService industryService;
    private final MemberRepository memberRepository;

    public OrganizationService(
            OrganizationRepository organizationRepository,
            OrganizationMapper organizationMapper,
            UserService userService,
            IndustryService industryService,
            MemberRepository memberRepository
    ) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
        this.userService = userService;
        this.industryService = industryService;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Organization create(OrganizationRequest request) {
        var founder = userService.findById(request.getFounderId());
        var industry = industryService.findByName(request.getIndustry());

        var organization = organizationMapper.mapToEntity(request, founder, industry);
        var savedOrganization = organizationRepository.save(organization);

        registerFounderMember(founder, savedOrganization);

        return savedOrganization;
    }

    private void registerFounderMember(User founder, Organization organization) {
        var founderMember = new Member(founder, organization, MemberRole.OWNER, null);
        memberRepository.save(founderMember);
    }

    public Organization findById(long id) {
        return organizationRepository.findWithIndustryAndHeadquartersById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization not found"));
    }

    public List<OrganizationSummaryResponse> search(Optional<String> opName) {
        var name = opName.map(String::strip).orElse("");
        return organizationRepository.search(name);
    }

    public List<OrganizationResponse> findAllByFounder(long id) {
        var founder = userService.findById(id);
        var organizations = organizationRepository.findAllByFounder(founder);
        return organizations.stream()
                .map(organizationMapper::mapToResponse)
                .toList();
    }

}
