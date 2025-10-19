package com.example.TripAgora.participation.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.exception.ApplicationException;

public class ParticipationException extends ApplicationException {
    public ParticipationException(ResponseCode errorCode) {
        super(errorCode);
    }
}