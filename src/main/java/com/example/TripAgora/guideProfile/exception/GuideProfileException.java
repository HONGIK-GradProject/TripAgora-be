package com.example.TripAgora.guideProfile.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.exception.ApplicationException;

public class GuideProfileException extends ApplicationException {
    public GuideProfileException(ResponseCode errorCode) {
        super(errorCode);
    }
}