package com.example.TripAgora.session.exception;

import com.example.TripAgora.common.code.SessionErrorCode;

public class InvalidMaxParticipantsException extends SessionException {
    public InvalidMaxParticipantsException() {
        super(SessionErrorCode.INVALID_MAX_PARTICIPANTS);
    }
}