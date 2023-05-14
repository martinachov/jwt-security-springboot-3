package com.martinachov.security.infrastructure.adapters.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martinachov.security.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    
}
