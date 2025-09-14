package com.example.TripAgora.tag.exception;

import com.example.TripAgora.common.code.TagErrorCode;

public class MaximumTagsExceededException extends TagException {
    public MaximumTagsExceededException() {
        super(TagErrorCode.MAXIMUM_TAGS_EXCEED);
    }
}