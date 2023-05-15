package com.martinachov.security.infrastructure.adapters.persistence.repositories;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.martinachov.security.domain.model.User;
import com.martinachov.security.domain.ports.outbound.UserRepository;
import com.martinachov.security.infrastructure.adapters.persistence.entities.UserEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final ModelMapper modelMapper;


    @Override
    public User save(User user) {
        UserEntity newUser = modelMapper.map(user, UserEntity.class);
        UserEntity userSaved = jpaUserRepository.save(newUser);
        return modelMapper.map(userSaved, User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return  jpaUserRepository.findByEmail(email)
                                    .map(userEntity -> User.builder()
                                                                .email(userEntity.getEmail())
                                                                .firstname(userEntity.getFirstname())
                                                                .lastname(userEntity.getLastname())
                                                                .password(userEntity.getPassword())
                                                                .build());

              
    }
    
}
