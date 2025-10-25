package com.example.TripAgora.room.exception;

import com.example.TripAgora.common.code.NoticeErrorCode;

public class NoticeNotFoundException extends NoticeException {
    public NoticeNotFoundException() {
        super(NoticeErrorCode.NOTICE_NOT_FOUND);
    }
}