package com.example.TripAgora.guideProfile.exception;

import com.example.TripAgora.common.code.GuideProfileErrorCode;

public class GuideProfileNotFoundException extends GuideProfileException {
    public GuideProfileNotFoundException() {
        super(GuideProfileErrorCode.ALREADY_GUIDE);
    }
}