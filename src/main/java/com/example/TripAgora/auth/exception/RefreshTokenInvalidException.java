package com.example.TripAgora.auth.exception;

import com.example.TripAgora.common.code.AuthErrorCode;

public class RefreshTokenInvalidException extends AuthException {
    public RefreshTokenInvalidException() {
        super(AuthErrorCode.REFRESH_TOKEN_INVALID);
    }
}