package com.example.TripAgora.participation.exception;

import com.example.TripAgora.common.code.ParticipationErrorCode;

public class ParticipationNotFoundException extends ParticipationException {
    public ParticipationNotFoundException() {
        super(ParticipationErrorCode.PARTICIPATION_NOT_FOUND);
    }
}