package com.example.TripAgora.review.exception;

import com.example.TripAgora.common.code.ReviewErrorCode;

public class AlreadyReviewedException extends ReviewException {
    public AlreadyReviewedException() {
        super(ReviewErrorCode.ALREADY_REVIEWED);
    }
}