package com.example.TripAgora.session.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.exception.ApplicationException;

public class SessionException extends ApplicationException {
    public SessionException(ResponseCode errorCode) {
        super(errorCode);
    }
}