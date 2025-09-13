package com.example.TripAgora.template.exception;

import com.example.TripAgora.common.code.TemplateErrorCode;

public class TemplateNotFoundException extends TemplateException {
    public TemplateNotFoundException() {
        super(TemplateErrorCode.TEMPLATE_NOT_FOUND);
    }
}