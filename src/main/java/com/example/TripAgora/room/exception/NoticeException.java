package com.example.TripAgora.room.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.exception.ApplicationException;

public class NoticeException extends ApplicationException {
    public NoticeException(ResponseCode errorCode) {
        super(errorCode);
    }
}