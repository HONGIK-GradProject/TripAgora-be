package com.example.TripAgora.user.exception;

import com.example.TripAgora.common.code.UserErrorCode;

public class InvalidNicknameFormatException extends UserException {
    public InvalidNicknameFormatException() {
        super(UserErrorCode.INVALID_NICKNAME_FORMAT);
    }
}