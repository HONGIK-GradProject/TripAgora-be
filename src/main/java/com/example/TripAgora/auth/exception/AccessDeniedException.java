package com.example.TripAgora.auth.exception;

import com.example.TripAgora.common.code.AuthErrorCode;

public class AccessDeniedException extends AuthException {
    public AccessDeniedException() {
        super(AuthErrorCode.ACCESS_DENIED);
    }
}