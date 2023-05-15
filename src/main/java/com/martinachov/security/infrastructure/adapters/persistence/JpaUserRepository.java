package com.martinachov.security.infrastructure.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martinachov.security.domain.model.User;
import com.martinachov.security.domain.ports.outbound.UserRepository;

public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {
}
