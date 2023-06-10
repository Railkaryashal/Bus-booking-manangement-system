package com.cts.projectBus.ProBus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.cts.projectBus.ProBus.entity.User;
import com.cts.projectBus.ProBus.exception.UserNotFoundException;
import com.cts.projectBus.ProBus.repository.UserRepository;
import com.cts.projectBus.ProBus.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserByEmail_ExistingEmail_ReturnsUser() throws UserNotFoundException {
        // Arrange
        String email = "test@example.com";
        User expectedUser = new User();
        expectedUser.setEmail(email);
        expectedUser.setUserName("Test User");

        when(userRepository.getUserById(email)).thenReturn(expectedUser);

        // Act
        User result = userService.getUserByEmail(email);

        // Assert
        assertEquals(expectedUser, result);
        verify(userRepository).getUserById(email);
    }

    @Test
    void testGetUserByEmail_NonExistingEmail_ThrowsUserNotFoundException() {
        // Arrange
        String email = "nonexisting@example.com";

        when(userRepository.getUserById(email)).thenReturn(null);

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserByEmail(email);
        });

        verify(userRepository).getUserById(email);
    }
}
