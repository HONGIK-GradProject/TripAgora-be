package com.example.TripAgora.session.exception;

import com.example.TripAgora.common.code.SessionErrorCode;

public class SessionDateConflictException extends SessionException {
    public SessionDateConflictException() {
        super(SessionErrorCode.SESSION_DATE_CONFLICT);
    }
}