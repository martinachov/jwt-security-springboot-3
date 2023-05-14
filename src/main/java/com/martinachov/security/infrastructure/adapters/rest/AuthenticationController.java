package com.martinachov.security.infrastructure.adapters.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping(value="/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponseDto> registerUser(@RequestBody UserRegistrationDataDto signUpRequest) throws Exception {    
        return ResponseEntity.ok(authenticationService.registerUser(signUpRequest));
    }

    @PostMapping(value="/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> loginUser(@RequestBody LoginCredentialsDto credentials){
            return ResponseEntity.ok(authenticationService.loginUser(credentials));
    }


}
