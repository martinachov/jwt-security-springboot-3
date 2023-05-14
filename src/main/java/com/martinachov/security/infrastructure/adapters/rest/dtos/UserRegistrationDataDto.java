package com.martinachov.security.infrastructure.adapters.rest.dtos;

import com.martinachov.security.domain.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDataDto {
    
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;

}
