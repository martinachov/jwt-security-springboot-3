package com.martinachov.security.domain.ports.outbound;

import java.util.Optional;

import com.martinachov.security.domain.model.User;

public interface UserRepository {
    
    Boolean existsByEmail(String email);

    User save(User user);

    Optional<User> findByEmail(String email);
}
