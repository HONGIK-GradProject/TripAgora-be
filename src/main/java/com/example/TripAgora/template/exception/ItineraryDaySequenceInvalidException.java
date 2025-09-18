package com.example.TripAgora.template.exception;

import com.example.TripAgora.common.code.TemplateErrorCode;

public class ItineraryDaySequenceInvalidException extends TemplateException {
    public ItineraryDaySequenceInvalidException() {
        super(TemplateErrorCode.ITINERARY_DAY_SEQUENCE_INVALID);
    }
}