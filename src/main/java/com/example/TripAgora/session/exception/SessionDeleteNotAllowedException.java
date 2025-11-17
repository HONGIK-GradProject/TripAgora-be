package com.example.TripAgora.session.exception;

import com.example.TripAgora.common.code.SessionErrorCode;

public class SessionDeleteNotAllowedException extends SessionException {
    public SessionDeleteNotAllowedException() {
        super(SessionErrorCode.SESSION_CANNOT_DELETE);
    }
}