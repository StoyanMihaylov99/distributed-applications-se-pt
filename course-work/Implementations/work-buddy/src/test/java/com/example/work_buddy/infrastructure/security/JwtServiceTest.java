package com.example.work_buddy.infrastructure.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secretKey", "NDA0RTYzNTI2NjU1NkE1ODZFMzI3MjM1NzUzODc4MkY0MTNGNDQyODQ3MkI0QjYyNTA2NDUzNjc1NjZCNTk3MA==");
        ReflectionTestUtils.setField(jwtService, "jwtExpiration", 900000L); // 15 min
        ReflectionTestUtils.setField(jwtService, "refreshExpiration", 604800000L); // 7 days
    }

    @Test
    void shouldGenerateAccessToken() {
        UserDetails userDetails = new User("test@example.com", "password", new ArrayList<>());
        String token = jwtService.generateToken(userDetails);
        
        assertNotNull(token);
        assertEquals("test@example.com", jwtService.extractUsername(token));
    }

    @Test
    void shouldGenerateRefreshToken() {
        UserDetails userDetails = new User("test@example.com", "password", new ArrayList<>());
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        
        assertNotNull(refreshToken);
        assertEquals("test@example.com", jwtService.extractUsername(refreshToken));
    }

    @Test
    void shouldValidateToken() {
        UserDetails userDetails = new User("test@example.com", "password", new ArrayList<>());
        String token = jwtService.generateToken(userDetails);
        
        assertTrue(jwtService.isTokenValid(token, userDetails));
    }
}
