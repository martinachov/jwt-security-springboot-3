package com.martinachov.security.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@AllArgsConstructor
public class UserAlreadyExistsException extends Exception{
    String message;
}
