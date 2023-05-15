package com.martinachov.security.domain.ports.inbound;

import com.martinachov.security.domain.exceptions.UserAlreadyExistsException;
import com.martinachov.security.domain.model.User;

public interface RegisterUserUseCase {
    
    public void execute(User user) throws UserAlreadyExistsException;
    
}
