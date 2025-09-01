package com.example.TripAgora.auth.exception;

import com.example.TripAgora.common.code.AuthErrorCode;

public class AccessTokenInvalidException extends AuthException {
    public AccessTokenInvalidException() {
        super(AuthErrorCode.ACCESS_TOKEN_INVALID);
    }
}