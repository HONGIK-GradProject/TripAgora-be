package com.example.TripAgora.auth.exception;

import com.example.TripAgora.common.code.AuthErrorCode;

public class SocialAccessTokenInvalidException extends AuthException {
    public SocialAccessTokenInvalidException() {
        super(AuthErrorCode.SOCIAL_ACCESS_TOKEN_INVALID);
    }
}