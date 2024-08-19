package com.recrutaibackend.mappers;

import com.recrutaibackend.dto.UserRequest;
import com.recrutaibackend.dto.UserResponse;
import com.recrutaibackend.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final AddressMapper addressMapper;

    public UserMapper(PasswordEncoder passwordEncoder, AddressMapper addressMapper) {
        this.passwordEncoder = passwordEncoder;
        this.addressMapper = addressMapper;
    }

    public User mapToEntity(UserRequest request) {
        return new User(
                request.firstName(),
                request.lastName(),
                request.headline(),
                request.email(),
                passwordEncoder.encode(request.password()),
                addressMapper.mapToEntity(request.location())
        );
    }

    public UserResponse mapToResponse(User entity) {
        return new UserResponse(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getHeadline(),
                entity.getEmail(),
                addressMapper.mapToResponse(entity.getLocation())
        );
    }
}
