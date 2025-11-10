package com.example.TripAgora.chat.exception;

import com.example.TripAgora.common.code.ChatErrorCode;

public class InvalidDestinationException extends ChatException {
    public InvalidDestinationException() {
        super(ChatErrorCode.INVALID_DESTINATION);
    }
}