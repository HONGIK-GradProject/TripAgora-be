package com.example.TripAgora.tag.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.exception.ApplicationException;

public class TagException extends ApplicationException {
    public TagException(ResponseCode errorCode) {
        super(errorCode);
    }
}