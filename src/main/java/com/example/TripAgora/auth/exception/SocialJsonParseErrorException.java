package com.example.TripAgora.auth.exception;

import com.example.TripAgora.common.code.AuthErrorCode;

public class SocialJsonParseErrorException extends AuthException {
    public SocialJsonParseErrorException() {
        super(AuthErrorCode.SOCIAL_JSON_PARSE_ERROR);
    }
}