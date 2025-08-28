package com.example.TripAgora.user.exception;

import com.example.TripAgora.common.code.UserErrorCode;

public class UserNotFoundException extends UserException {
    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}