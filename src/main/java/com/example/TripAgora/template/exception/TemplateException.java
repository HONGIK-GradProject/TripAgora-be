package com.example.TripAgora.template.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.exception.ApplicationException;

public class TemplateException extends ApplicationException {
    public TemplateException(ResponseCode errorCode) {
        super(errorCode);
    }
}