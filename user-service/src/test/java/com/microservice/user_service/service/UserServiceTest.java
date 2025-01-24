package com.microservice.user_service.service;

import com.microservice.user_service.dto.UserRequest;
import com.microservice.user_service.dto.UserResponse;
import com.microservice.user_service.entity.User;
import com.microservice.user_service.exception.UserNotFoundException;
import com.microservice.user_service.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserRequest userRequest;
    private UserResponse userResponse;
    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setUsername("johndoe");
        user.setPassword("password123");
        user.setEmail("john.doe@example.com");

        userRequest = new UserRequest();
        userRequest.setName("John Doe");
        userRequest.setUsername("johndoe");
        userRequest.setPassword("password123");
        userRequest.setEmail("john.doe@example.com");

        userResponse = new UserResponse();
        userResponse.setId(1L);
        userResponse.setName("John Doe");
        userResponse.setUsername("johndoe");
        userResponse.setEmail("john.doe@example.com");
    }
    @Test
    void createUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(any(UserRequest.class), eq(User.class))).thenReturn(user);
        when(modelMapper.map(any(User.class), eq(UserResponse.class))).thenReturn(userResponse);

        UserResponse response = userService.createUser(userRequest);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        when(modelMapper.map(any(User.class), eq(UserResponse.class))).thenReturn(userResponse);

        List<UserResponse> users = userService.getAllUsers();

        assertEquals(1, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(modelMapper.map(any(User.class), eq(UserResponse.class))).thenReturn(userResponse);

        UserResponse response = userService.getUserById(1L).orElse(null);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserByIdNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    public void testUpdateUser_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(any(User.class), eq(UserResponse.class))).thenReturn(userResponse);

        UserResponse result = userService.updateUser(1L, userRequest);

        assertEquals(userResponse, result);
        verify(userRepository).findById(1L);
        verify(userRepository).save(any(User.class));
        verify(modelMapper).map(any(User.class), eq(UserResponse.class));
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, userRequest));

        verify(userRepository).findById(1L);
        verify(userRepository, never()).save(any(User.class));
        verify(modelMapper, never()).map(any(User.class), eq(UserResponse.class));
    }

    @Test
    void deleteUser() {
        when(userRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(userRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> userService.deleteUser(1L));
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUserNotFound() {
        when(userRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(RuntimeException.class, () -> userService.deleteUser(1L));
    }
}