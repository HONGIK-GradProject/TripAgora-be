package com.example.TripAgora.common.image.exception;

import com.example.TripAgora.common.code.ImageErrorCode;

public class UnSupportedImageFormatException extends ImageException {
    public UnSupportedImageFormatException() {
        super(ImageErrorCode.UNSUPPORTED_IMAGE_FORMAT);
    }
}