package com.example.TripAgora.user.exception;

import com.example.TripAgora.common.code.UserErrorCode;

public class DuplicateNicknameException extends UserException {
    public DuplicateNicknameException() {
        super(UserErrorCode.DUPLICATE_NICKNAME);
    }
}