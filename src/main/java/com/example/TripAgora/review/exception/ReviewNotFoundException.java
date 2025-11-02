package com.example.TripAgora.review.exception;

import com.example.TripAgora.common.code.ReviewErrorCode;

public class ReviewNotFoundException extends ReviewException {
    public ReviewNotFoundException() {
        super(ReviewErrorCode.REVIEW_NOT_FOUND);
    }
}