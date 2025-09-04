package com.example.TripAgora.tag.exception;

import com.example.TripAgora.common.code.TagErrorCode;

public class InvalidTagSelectionException extends TagException {
    public InvalidTagSelectionException() {
        super(TagErrorCode.INVALID_TAG_SELECTION);
    }
}