package com.microservice.user_service.controller;

import com.microservice.user_service.dto.UserRequest;
import com.microservice.user_service.dto.UserResponse;
import com.microservice.user_service.service.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserRequest userRequest;
    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
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
        when(userService.createUser(userRequest)).thenReturn(userResponse);

        ResponseEntity<UserResponse> response = userController.createUser(userRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    void getAllUsers() {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(userResponse));

        ResponseEntity<List<UserResponse>> response = userController.getAllUsers();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getUserById() {
        when(userService.getUserById(anyLong())).thenReturn(Optional.of(userResponse));

        ResponseEntity<UserResponse> response = userController.getUserById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    void getUserByIdNotFound() {
        when(userService.getUserById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<UserResponse> response = userController.getUserById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateUser() {
        when(userService.updateUser(anyLong(), any(UserRequest.class))).thenReturn(userResponse);
        ResponseEntity<UserResponse> response = userController.updateUser(1L, userRequest);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    void deleteUser() {
        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }
}