package com.example.work_buddy.infrastructure.security;

import com.example.work_buddy.application.dto.UserDto;
import com.example.work_buddy.application.service.interfaces.UserService;
import com.example.work_buddy.domain.exception.InvalidTokenException;
import com.example.work_buddy.infrastructure.security.dto.AuthResponse;
import com.example.work_buddy.infrastructure.security.dto.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthService(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public AuthResponse register(UserDto request) {
        userService.createUser(request);
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
        String jwtToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        return new AuthResponse(jwtToken, refreshToken);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
        String jwtToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        return new AuthResponse(jwtToken, refreshToken);
    }

    public AuthResponse refreshToken(String refreshToken) {
        final String userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                String accessToken = jwtService.generateToken(userDetails);
                return new AuthResponse(accessToken, refreshToken);
            }
        }
        throw new InvalidTokenException("Invalid refresh token");
    }
}
