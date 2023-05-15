package com.martinachov.security.domain.ports.outbound;

import java.util.Optional;

import com.martinachov.security.domain.model.User;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(String email);
}
