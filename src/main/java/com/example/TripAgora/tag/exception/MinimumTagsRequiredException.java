package com.example.TripAgora.tag.exception;

import com.example.TripAgora.common.code.TagErrorCode;

public class MinimumTagsRequiredException extends TagException {
    public MinimumTagsRequiredException() {
        super(TagErrorCode.MINIMUM_TAGS_REQUIRED);
    }
}