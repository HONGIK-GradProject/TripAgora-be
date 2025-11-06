package com.example.TripAgora.session.exception;

import com.example.TripAgora.common.code.SessionErrorCode;

public class SessionNotInProgressException extends SessionException {
    public SessionNotInProgressException() {
        super(SessionErrorCode.SESSION_NOT_IN_PROGRESS);
    }
}