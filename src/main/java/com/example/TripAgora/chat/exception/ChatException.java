package com.example.TripAgora.chat.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.exception.ApplicationException;

public class ChatException extends ApplicationException {
    public ChatException(ResponseCode errorCode) {
        super(errorCode);
    }
}