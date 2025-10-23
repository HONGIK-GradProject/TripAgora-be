package com.example.TripAgora.participation.exception;

import com.example.TripAgora.common.code.ParticipationErrorCode;

public class CannotParticipateInOwnSessionException extends ParticipationException {
    public CannotParticipateInOwnSessionException() {
        super(ParticipationErrorCode.CANNOT_PARTICIPATE_IN_OWN_SESSION);
    }
}