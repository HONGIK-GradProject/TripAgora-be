package com.example.TripAgora.common.code;

import org.springframework.http.HttpStatus;

public interface ResponseCode {
    HttpStatus getHttpStatus();
    String getMessage();
    default int getStatusValue() {
        return getHttpStatus().value();
    }
}