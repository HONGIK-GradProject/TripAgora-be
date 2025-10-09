package com.example.TripAgora.session.exception;

import com.example.TripAgora.common.code.SessionErrorCode;

public class SessionNotFoundException extends SessionException {
    public SessionNotFoundException() {
        super(SessionErrorCode.SESSION_NOT_FOUND);
    }
}