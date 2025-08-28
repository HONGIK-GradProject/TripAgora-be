package com.example.TripAgora.user.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.exception.ApplicationException;

public class UserException extends ApplicationException {
    public UserException(ResponseCode errorCode) {
        super(errorCode);
    }
}