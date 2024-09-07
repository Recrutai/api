package com.recrutaibackend.institution;

import com.recrutaibackend.auth.user.User;
import com.recrutaibackend.auth.user.UserService;
import com.recrutaibackend.industry.IndustryService;
import com.recrutaibackend.institution.member.Member;
import com.recrutaibackend.institution.member.MemberRepository;
import com.recrutaibackend.institution.member.MemberRole;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final InstitutionMapper institutionMapper;
    private final UserService userService;
    private final IndustryService industryService;
    private final MemberRepository memberRepository;

    public InstitutionService(
            InstitutionRepository institutionRepository,
            InstitutionMapper institutionMapper,
            UserService userService,
            IndustryService industryService,
            MemberRepository memberRepository
    ) {
        this.institutionRepository = institutionRepository;
        this.institutionMapper = institutionMapper;
        this.userService = userService;
        this.industryService = industryService;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Institution create(InstitutionRequest request) {
        var founder = userService.findById(request.founderId());
        var industry = industryService.findByName(request.industry());

        var institution = institutionMapper.mapToEntity(request, founder, industry);
        var savedInstitution = institutionRepository.save(institution);

        registerFounderMember(founder, savedInstitution);

        return savedInstitution;
    }

    private void registerFounderMember(User founder, Institution institution) {
        var founderMember = new Member(founder, institution, MemberRole.OWNER, null);
        memberRepository.save(founderMember);
    }

    public Institution findById(long id) {
        return institutionRepository.findWithIndustryAndHeadquartersById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Institution not found"));
    }

    public List<InstitutionResponse> findAll() {
        return institutionRepository.findAllWithIndustryAndHeadquartersBy()
                .stream()
                .map(institutionMapper::mapToResponse)
                .toList();
    }

}
