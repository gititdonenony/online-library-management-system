package com.microservice.user_service.service;

import com.microservice.user_service.dto.UserRequest;
import com.microservice.user_service.dto.UserResponse;
import com.microservice.user_service.entity.User;
import com.microservice.user_service.exception.UserNotFoundException;
import com.microservice.user_service.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserResponse createUser(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    public Optional<UserResponse> getUserById(Long id) {
        return Optional.ofNullable(userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserResponse.class))
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id)));
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setName(userRequest.getName());
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserResponse.class);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
