package com.example.work_buddy.infrastructure.security;

import com.example.work_buddy.application.service.interfaces.UserService;
import com.example.work_buddy.domain.exception.InvalidTokenException;
import com.example.work_buddy.infrastructure.security.dto.AuthResponse;
import com.example.work_buddy.infrastructure.security.dto.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    private AuthService authService;

    @Mock
    private UserService userService;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthService(userService, jwtService, authenticationManager, userDetailsService);
    }

    @Test
    void login_ShouldReturnBothTokens() {
        LoginRequest request = new LoginRequest("test@example.com", "password");
        UserDetails userDetails = new User("test@example.com", "password", new ArrayList<>());
        
        when(userDetailsService.loadUserByUsername("test@example.com")).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn("access-token");
        when(jwtService.generateRefreshToken(userDetails)).thenReturn("refresh-token");

        AuthResponse response = authService.login(request);

        assertNotNull(response);
        assertEquals("access-token", response.token());
        assertEquals("refresh-token", response.refreshToken());
        verify(authenticationManager).authenticate(any());
    }

    @Test
    void refreshToken_ShouldReturnNewAccessToken() {
        String refreshToken = "valid-refresh-token";
        UserDetails userDetails = new User("test@example.com", "password", new ArrayList<>());
        
        when(jwtService.extractUsername(refreshToken)).thenReturn("test@example.com");
        when(userDetailsService.loadUserByUsername("test@example.com")).thenReturn(userDetails);
        when(jwtService.isTokenValid(refreshToken, userDetails)).thenReturn(true);
        when(jwtService.generateToken(userDetails)).thenReturn("new-access-token");

        AuthResponse response = authService.refreshToken(refreshToken);

        assertNotNull(response);
        assertEquals("new-access-token", response.token());
        assertEquals(refreshToken, response.refreshToken());
    }

    @Test
    void refreshToken_ShouldThrowException_WhenTokenInvalid() {
        String refreshToken = "invalid-token";
        
        when(jwtService.extractUsername(refreshToken)).thenReturn("test@example.com");
        when(userDetailsService.loadUserByUsername("test@example.com")).thenReturn(new User("test@example.com", "password", new ArrayList<>()));
        when(jwtService.isTokenValid(eq(refreshToken), any())).thenReturn(false);

        assertThrows(InvalidTokenException.class, () -> authService.refreshToken(refreshToken));
    }
}
