package com.example.TripAgora.guideProfile.exception;

import com.example.TripAgora.common.code.GuideProfileErrorCode;

public class AlreadyGuideException extends GuideProfileException {
    public AlreadyGuideException() {
        super(GuideProfileErrorCode.ALREADY_GUIDE);
    }
}