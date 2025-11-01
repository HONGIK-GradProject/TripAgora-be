package com.example.TripAgora.review.exception;

import com.example.TripAgora.common.code.ReviewErrorCode;

public class SessionNotCompletedException extends ReviewException {
    public SessionNotCompletedException() {
        super(ReviewErrorCode.SESSION_NOT_COMPLETED);
    }
}