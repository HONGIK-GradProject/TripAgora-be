package com.example.TripAgora.participation.exception;

import com.example.TripAgora.common.code.ParticipationErrorCode;

public class AlreadyParticipatingException extends ParticipationException {
    public AlreadyParticipatingException() {
        super(ParticipationErrorCode.ALREADY_PARTICIPATING);
    }
}