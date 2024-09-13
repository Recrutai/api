package com.recrutai.api.auth.user;

import com.recrutai.api.address.AddressMapper;
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
                request.getFirstName(),
                request.getLastName(),
                request.getHeadline(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                addressMapper.mapToEntity(request.getLocation())
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
