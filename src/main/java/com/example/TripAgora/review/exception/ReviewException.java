package com.example.TripAgora.review.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.exception.ApplicationException;

public class ReviewException extends ApplicationException {
    public ReviewException(ResponseCode errorCode) {
        super(errorCode);
    }
}