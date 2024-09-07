package com.recrutaibackend.profile.employment;

import com.recrutaibackend.address.AddressMapper;
import com.recrutaibackend.auth.user.User;
import com.recrutaibackend.institution.Institution;
import com.recrutaibackend.shared.DateUtils;
import org.springframework.stereotype.Service;

@Service
public class EmploymentMapper {
    private final AddressMapper addressMapper;

    public EmploymentMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public Employment mapToEntity(EmploymentRequest request, User user, Institution institution) {
        return new Employment(
                user,
                institution,
                request.title(),
                request.type(),
                request.workModel(),
                addressMapper.mapToEntity(request.address()),
                request.description(),
                DateUtils.convertYearMonthToNumber(request.startDate()),
                request.endDate() != null ? DateUtils.convertYearMonthToNumber(request.endDate()) : null
        );
    }

    public EmploymentResponse mapToResponse(Employment entity) {
        return new EmploymentResponse(
                entity.getId(),
                entity.getInstitution().getId(),
                entity.getTitle(),
                entity.getType().toString(),
                entity.getWorkModel().toString(),
                addressMapper.mapToResponse(entity.getAddress()),
                entity.getDescription(),
                DateUtils.convertNumberToYearMonth(entity.getStartDate()),
                entity.getEndDate() != null ? DateUtils.convertNumberToYearMonth(entity.getEndDate()) : null
        );
    }

}
