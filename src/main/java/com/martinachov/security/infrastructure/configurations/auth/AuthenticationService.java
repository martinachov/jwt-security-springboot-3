package com.martinachov.security.infrastructure.configurations.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.martinachov.security.domain.model.User;
import com.martinachov.security.infrastructure.adapters.persistence.JpaUserRepository;
import com.martinachov.security.infrastructure.adapters.rest.dtos.LoginCredentialsDto;
import com.martinachov.security.infrastructure.adapters.rest.dtos.TokenDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JpaUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public TokenDto loginUser(LoginCredentialsDto credentials) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
        );

        User user = userRepository.findByEmail(credentials.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user.getEmail());

        return TokenDto.builder().token(token).build();
    }
    
}
