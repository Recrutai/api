package com.recrutaibackend.auth.user;

import com.recrutaibackend.address.AddressMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapToEntity(UserRequest request) {
        return new User(
                request.firstName(),
                request.lastName(),
                request.headline(),
                request.email(),
                passwordEncoder.encode(request.password())
        );
    }

    public UserResponse mapToResponse(User entity) {
        return new UserResponse(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getHeadline(),
                entity.getEmail()
        );
    }
}
