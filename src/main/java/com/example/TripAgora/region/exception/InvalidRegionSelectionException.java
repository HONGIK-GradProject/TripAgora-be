package com.example.TripAgora.region.exception;

import com.example.TripAgora.common.code.RegionErrorCode;

public class InvalidRegionSelectionException extends RegionException {
    public InvalidRegionSelectionException() {
        super(RegionErrorCode.INVALID_REGION_SELECTION);
    }
}