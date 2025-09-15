package com.example.TripAgora.region.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.exception.ApplicationException;

public class RegionException extends ApplicationException {
    public RegionException(ResponseCode errorCode) {
        super(errorCode);
    }
}