package com.example.TripAgora.participation.exception;

import com.example.TripAgora.common.code.ParticipationErrorCode;

public class ParticipationCancelNotAllowedException extends ParticipationException {
    public ParticipationCancelNotAllowedException() {
        super(ParticipationErrorCode.PARTICIPATION_CANCEL_NOT_ALLOWED);
    }
}