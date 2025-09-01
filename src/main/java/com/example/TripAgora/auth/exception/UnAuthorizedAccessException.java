package com.example.TripAgora.auth.exception;

import com.example.TripAgora.common.code.AuthErrorCode;

public class UnAuthorizedAccessException extends AuthException {
    public UnAuthorizedAccessException() {
        super(AuthErrorCode.UNAUTHORIZED_ACCESS);
    }
}