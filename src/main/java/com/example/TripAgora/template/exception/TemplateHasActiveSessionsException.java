package com.example.TripAgora.template.exception;

import com.example.TripAgora.common.code.TemplateErrorCode;

public class TemplateHasActiveSessionsException extends TemplateException {
    public TemplateHasActiveSessionsException() {
        super(TemplateErrorCode.TEMPLATE_HAS_ACTIVE_SESSIONS);
    }
}