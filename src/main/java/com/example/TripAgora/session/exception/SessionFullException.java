package com.example.TripAgora.session.exception;

import com.example.TripAgora.common.code.SessionErrorCode;

public class SessionFullException extends SessionException {
    public SessionFullException() {
        super(SessionErrorCode.SESSION_FULL);
    }
}