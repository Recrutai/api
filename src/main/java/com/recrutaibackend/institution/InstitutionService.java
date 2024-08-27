package com.recrutaibackend.institution;

import com.recrutaibackend.address.AddressMapper;
import com.recrutaibackend.address.AddressRequest;
import com.recrutaibackend.institution.member.MemberRequest;
import com.recrutaibackend.institution.member.MemberResponse;
import com.recrutaibackend.institution.member.MemberService;
import com.recrutaibackend.institution.school.School;
import com.recrutaibackend.institution.school.SchoolMapper;
import com.recrutaibackend.institution.school.SchoolRepository;
import com.recrutaibackend.institution.school.SchoolRequest;
import com.recrutaibackend.institution.industry.Industry;
import com.recrutaibackend.institution.industry.IndustryRepository;
import com.recrutaibackend.auth.user.UserService;
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
    private final InstitutionSizeRepository institutionSizeRepository;
    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;
    private final IndustryRepository industryRepository;
    private final MemberService memberService;
    private final AddressMapper addressMapper;

    public InstitutionService(
            InstitutionRepository institutionRepository,
            InstitutionMapper institutionMapper,
            UserService userService,
            InstitutionSizeRepository institutionSizeRepository,
            SchoolMapper schoolMapper,
            SchoolRepository schoolRepository,
            IndustryRepository industryRepository,
            MemberService memberService, AddressMapper addressMapper) {
        this.institutionRepository = institutionRepository;
        this.institutionMapper = institutionMapper;
        this.userService = userService;
        this.institutionSizeRepository = institutionSizeRepository;
        this.schoolMapper = schoolMapper;
        this.schoolRepository = schoolRepository;
        this.industryRepository = industryRepository;
        this.memberService = memberService;
        this.addressMapper = addressMapper;
    }

    public Institution findById(long id) {
        return institutionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Institution not found"));
    }

    @Transactional
    public Institution create(InstitutionRequest request) {
        var owner = userService.findById(request.ownerId());
        var industry = this.findIndustryByName(request.industry());
        var companySize = this.findSizeById(request.companySizeId());

        var institution = institutionMapper.mapToEntity(request, owner, industry, companySize);

        return institutionRepository.save(institution);
    }

    @Transactional
    public School createSchool(SchoolRequest request) {
        var institution = this.create(request.institution());
        var schoolSize = this.findSizeById(request.schoolSizeId());

        var school = schoolMapper.mapToEntity(institution, schoolSize);

        return schoolRepository.save(school);
    }

    public List<InstitutionResponse> findAll() {
        return institutionRepository.findAll()
                .stream()
                .map(institutionMapper::mapToResponse)
                .toList();
    }

    public List<MemberResponse> findAllMembers(long id) {
        return memberService.findAllByInstitutionId(id);
    }

    public MemberResponse createMember(long id, MemberRequest request) {
        var institution = this.findById(id);
        return memberService.create(institution, request);
    }

    private Industry findIndustryByName(String name) {
        return industryRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Industry not found"));
    }

    public List<Industry> findAllIndustries() {
        return industryRepository.findAll();
    }

    private InstitutionSize findSizeById(short id) {
        return institutionSizeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Institution size not found"));
    }

    public List<InstitutionSize> findAllSizes() {
        return institutionSizeRepository.findAll();
    }

    public void setAddress(AddressRequest request, Long id) {
        var institution = institutionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Institution not found"));
        institution.setHeadquarters(addressMapper.mapToEntity(request));
        institutionRepository.save(institution);
    }
}
