package com.recrutaibackend.service;

import com.recrutaibackend.dto.UserRequest;
import com.recrutaibackend.mappers.UserMapper;
import com.recrutaibackend.model.User;
import com.recrutaibackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User create(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already taken");
        }
        var user = userMapper.mapToEntity(userRequest);
        return userRepository.save(user);
    }

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public void activateUser(User user) {
        user.setIsActive(true);
        userRepository.save(user);
    }

}
