package com.example.TripAgora.room.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.exception.ApplicationException;

public class RoomException extends ApplicationException {
    public RoomException(ResponseCode errorCode) {
        super(errorCode);
    }
}