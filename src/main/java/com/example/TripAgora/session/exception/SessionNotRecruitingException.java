package com.example.TripAgora.session.exception;

import com.example.TripAgora.common.code.SessionErrorCode;

public class SessionNotRecruitingException extends SessionException {
    public SessionNotRecruitingException() {
        super(SessionErrorCode.SESSION_NOT_RECRUITING);
    }
}