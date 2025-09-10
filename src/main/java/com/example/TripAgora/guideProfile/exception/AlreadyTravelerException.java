package com.example.TripAgora.guideProfile.exception;

import com.example.TripAgora.common.code.GuideProfileErrorCode;

public class AlreadyTravelerException extends GuideProfileException {
    public AlreadyTravelerException() {
        super(GuideProfileErrorCode.ALREADY_TRAVELER);
    }
}