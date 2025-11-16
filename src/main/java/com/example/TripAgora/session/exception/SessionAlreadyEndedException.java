package com.example.TripAgora.session.exception;

import com.example.TripAgora.common.code.SessionErrorCode;

public class SessionAlreadyEndedException extends SessionException {
    public SessionAlreadyEndedException() {
        super(SessionErrorCode.SESSION_ALREADY_ENDED);
    }
}