package com.example.TripAgora.stomp.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.exception.ApplicationException;

public class StompException extends ApplicationException {
    public StompException(ResponseCode errorCode) {
        super(errorCode);
    }
}