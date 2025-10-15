package com.example.TripAgora.common.image.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.exception.ApplicationException;

public class ImageException extends ApplicationException {
    public ImageException(ResponseCode errorCode) {
        super(errorCode);
    }
}