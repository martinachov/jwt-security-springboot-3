package com.martinachov.security.domain.ports.inbound;

import com.martinachov.security.domain.model.User;

public interface AuthenticationInputPort {
    
    public void registerUser(User user);
    
}
