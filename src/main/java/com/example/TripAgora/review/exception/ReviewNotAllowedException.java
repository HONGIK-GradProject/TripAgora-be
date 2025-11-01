package com.example.TripAgora.review.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.code.ReviewErrorCode;

public class ReviewNotAllowedException extends ReviewException{
    public ReviewNotAllowedException() {
        super(ReviewErrorCode.REVIEW_NOT_ALLOWED);
    }
}