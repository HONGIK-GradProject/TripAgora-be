package com.example.TripAgora.participation.exception;

import com.example.TripAgora.common.code.ParticipationErrorCode;

public class ConflictingParticipationException extends ParticipationException {
    public ConflictingParticipationException() {
        super(ParticipationErrorCode.CONFLICTING_PARTICIPATION);
    }
}