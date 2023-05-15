package com.martinachov.security.infrastructure.adapters.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martinachov.security.infrastructure.adapters.persistence.entities.UserEntity;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    public Optional<UserEntity> findByEmail(String email);

}
