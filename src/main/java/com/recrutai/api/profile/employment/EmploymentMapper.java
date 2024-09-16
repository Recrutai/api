package com.recrutai.api.profile.employment;

import com.recrutai.api.address.AddressMapper;
import com.recrutai.api.auth.user.User;
import com.recrutai.api.institution.Institution;
import com.recrutai.api.institution.InstitutionMapper;
import com.recrutai.api.shared.DateUtils;
import org.springframework.stereotype.Service;

@Service
public class EmploymentMapper {
    private final AddressMapper addressMapper;
    private final InstitutionMapper institutionMapper;

    public EmploymentMapper(AddressMapper addressMapper, InstitutionMapper institutionMapper) {
        this.addressMapper = addressMapper;
        this.institutionMapper = institutionMapper;
    }

    public Employment mapToEntity(EmploymentRequest request, User user, Institution institution) {
        return new Employment(
                user,
                institution,
                request.getFallbackInstitutionName(),
                request.getTitle(),
                request.getType(),
                request.getWorkModel(),
                addressMapper.mapToEntity(request.getAddress()),
                request.getDescription(),
                DateUtils.convertYearMonthToNumber(request.getStartDate()),
                request.getEndDate() != null ? DateUtils.convertYearMonthToNumber(request.getEndDate()) : null
        );
    }

    public EmploymentResponse mapToResponse(Employment entity) {
        return new EmploymentResponse(
                entity.getId(),
                institutionMapper.mapToSummaryResponse(entity.getInstitution()),
                entity.getFallbackInstitutionName(),
                entity.getTitle(),
                entity.getType().toString(),
                entity.getWorkModel().toString(),
                entity.getAddress().getCity() + ", " + entity.getAddress().getState(),
                entity.getDescription(),
                DateUtils.convertNumberToYearMonth(entity.getStartDate()),
                entity.getEndDate() != null ? DateUtils.convertNumberToYearMonth(entity.getEndDate()) : null
        );
    }

}
