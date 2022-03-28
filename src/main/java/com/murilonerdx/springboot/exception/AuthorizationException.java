package com.murilonerdx.springboot.exception;

import org.springframework.security.authentication.InternalAuthenticationServiceException;

public class AuthorizationException extends InternalAuthenticationServiceException {
    public AuthorizationException(String expired_or_invalid_jwt_token) {
        super(expired_or_invalid_jwt_token);
    }
}
