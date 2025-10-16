package com.example.TripAgora.common.image.exception;

import com.example.TripAgora.common.code.ImageErrorCode;

public class ImageDeletionFailedException extends ImageException {
    public ImageDeletionFailedException() {
        super(ImageErrorCode.IMAGE_DELETION_FAILED);
    }
}