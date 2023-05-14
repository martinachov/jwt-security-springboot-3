package com.martinachov.security.infrastructure.configurations.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.martinachov.security.domain.exceptions.UserAlreadyExistsException;
import com.martinachov.security.domain.model.User;
import com.martinachov.security.infrastructure.adapters.persistence.UserRepository;
import com.martinachov.security.infrastructure.adapters.rest.dtos.LoginCredentialsDto;
import com.martinachov.security.infrastructure.adapters.rest.dtos.MessageResponseDto;
import com.martinachov.security.infrastructure.adapters.rest.dtos.TokenDto;
import com.martinachov.security.infrastructure.adapters.rest.dtos.UserRegistrationDataDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;


    public MessageResponseDto registerUser(UserRegistrationDataDto signUpRequest) throws UserAlreadyExistsException {
        
        if(userRepository.findByEmail(signUpRequest.getEmail()).isPresent())
            throw new UserAlreadyExistsException("User already exist !!");
            
        User user = User.builder()
                            .firstname(signUpRequest.getFirstname())
                            .lastname(signUpRequest.getLastname())
                            .email(signUpRequest.getEmail())
                            .password(passwordEncoder.encode(signUpRequest.getPassword()))
                        .build();

        userRepository.save(user);    
        return MessageResponseDto.builder().message("User createad !!").build();
    }

    public TokenDto loginUser(LoginCredentialsDto credentials) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
        );

        User user = userRepository.findByEmail(credentials.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user.getEmail());

        return TokenDto.builder().token(token).build();
    }
    
}
