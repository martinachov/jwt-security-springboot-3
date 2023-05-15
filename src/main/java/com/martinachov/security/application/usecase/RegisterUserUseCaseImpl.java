package com.martinachov.security.application.usecase;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.martinachov.security.domain.exceptions.UserAlreadyExistsException;
import com.martinachov.security.domain.model.User;
import com.martinachov.security.domain.ports.inbound.RegisterUserUseCase;
import com.martinachov.security.domain.ports.outbound.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute(User user) throws UserAlreadyExistsException {
        if(userRepository.existsByEmail(user.getEmail()))
           throw  new UserAlreadyExistsException("User already exist !!");

        User newUser = User.builder()
                                .email(user.getEmail())
                                .firstname(user.getFirstname())
                                .lastname(user.getLastname())
                                .password(passwordEncoder.encode(user.getPassword()))
                                .build();
                                
        userRepository.save(newUser);   
    }
    
}
