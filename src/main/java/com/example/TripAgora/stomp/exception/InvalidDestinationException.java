package com.example.TripAgora.stomp.exception;

import com.example.TripAgora.common.code.StompErrorCode;

public class InvalidDestinationException extends StompException {
    public InvalidDestinationException() {
        super(StompErrorCode.INVALID_DESTINATION);
    }
}