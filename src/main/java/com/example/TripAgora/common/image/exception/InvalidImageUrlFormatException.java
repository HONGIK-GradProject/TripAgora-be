package com.example.TripAgora.common.image.exception;

import com.example.TripAgora.common.code.ImageErrorCode;

public class InvalidImageUrlFormatException extends ImageException {
    public InvalidImageUrlFormatException() {
        super(ImageErrorCode.INVALID_IMAGE_URL_FORMAT);
    }
}