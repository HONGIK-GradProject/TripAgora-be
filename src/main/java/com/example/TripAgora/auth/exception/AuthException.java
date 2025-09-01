package com.example.TripAgora.auth.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.exception.ApplicationException;

public class AuthException extends ApplicationException {
    public AuthException(ResponseCode errorCode) {
        super(errorCode);
    }
}