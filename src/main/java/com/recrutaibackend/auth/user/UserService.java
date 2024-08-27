package com.recrutaibackend.auth.user;

import com.recrutaibackend.address.AddressMapper;
import com.recrutaibackend.address.AddressRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper, AddressMapper addressMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
    }

    public User create(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already taken");
        }
        var user = userMapper.mapToEntity(userRequest);
        return userRepository.save(user);
    }

    public User findById(long id) {
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

    public void setAddress(AddressRequest request, Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.setLocation(addressMapper.mapToEntity(request));
        userRepository.save(user);

    }

}
