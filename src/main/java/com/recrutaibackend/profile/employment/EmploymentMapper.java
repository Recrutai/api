package com.recrutaibackend.profile.employment;

import com.recrutaibackend.address.AddressMapper;
import com.recrutaibackend.institution.Institution;
import com.recrutaibackend.institution.InstitutionMapper;
import com.recrutaibackend.auth.user.User;
import com.recrutaibackend.auth.user.UserMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmploymentMapper {

    private final AddressMapper addressMapper;
    private final UserMapper userMapper;
    private final InstitutionMapper institutionMapper;

    public EmploymentMapper(AddressMapper addressMapper, UserMapper userMapper, InstitutionMapper institutionMapper) {
        this.addressMapper = addressMapper;
        this.userMapper = userMapper;
        this.institutionMapper = institutionMapper;
    }

    public Employment mapToEntity(EmploymentRequest request, User user, Institution institution, EmploymentType type) {
        return new Employment(
                user,
                institution,
                request.title(),
                type,
                request.workModel(),
                addressMapper.mapToEntity(request.workAddress()),
                request.description(),
                this.convertDateToYYYYMM(request.startDate()),
                this.convertDateToYYYYMM(request.endDate())
        );
    }

    public EmploymentResponse mapToResponse(Employment employment) {
        return new EmploymentResponse(
                employment.getId(),
                userMapper.mapToResponse(employment.getUser()),
                institutionMapper.mapToResponse(employment.getInstitution()),
                employment.getTitle(),
                employment.getType().getName(),
                employment.getWorkModel().toString(),
                addressMapper.mapToResponse(employment.getWorkAddress()),
                employment.getDescription(),
                this.convertYYYYMMToDate(employment.getStartDate()),
                this.convertYYYYMMToDate(employment.getEndDate())
        );
    }

    private int convertDateToYYYYMM(LocalDate date) {
        return date.getYear() * 100 + date.getMonthValue();
    }

    private LocalDate convertYYYYMMToDate(Integer yearAndMonth) {
        return LocalDate.of(yearAndMonth / 100, yearAndMonth % 100, 1);
    }
}
