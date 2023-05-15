package com.martinachov.security.infrastructure.adapters.rest;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martinachov.security.application.usecase.RegisterUserUseCaseImpl;
import com.martinachov.security.domain.model.User;
import com.martinachov.security.infrastructure.adapters.rest.dtos.LoginCredentialsDto;
import com.martinachov.security.infrastructure.adapters.rest.dtos.MessageResponseDto;
import com.martinachov.security.infrastructure.adapters.rest.dtos.TokenDto;
import com.martinachov.security.infrastructure.adapters.rest.dtos.UserRegistrationDataDto;
import com.martinachov.security.infrastructure.configurations.auth.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;
    private final ModelMapper modelMapper;
    private final RegisterUserUseCaseImpl registerUser;

    @PostMapping(value="/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponseDto> registerUser(@RequestBody UserRegistrationDataDto signUpRequest) throws Exception {    
        User newUser = modelMapper.map(signUpRequest, User.class);
        registerUser.execute(newUser);
        return ResponseEntity.ok(MessageResponseDto.builder().message("User createad !!").build());
    }

    @PostMapping(value="/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> loginUser(@RequestBody LoginCredentialsDto credentials){
            return ResponseEntity.ok(authenticationService.loginUser(credentials));
    }


}
