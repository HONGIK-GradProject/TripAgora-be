package com.example.TripAgora.common.image.exception;

import com.example.TripAgora.common.code.ImageErrorCode;

public class ImageUploadFailedException extends ImageException {
    public ImageUploadFailedException() {
        super(ImageErrorCode.IMAGE_UPLOAD_FAILED);
    }
}