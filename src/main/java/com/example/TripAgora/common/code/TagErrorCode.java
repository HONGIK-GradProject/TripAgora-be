package com.example.TripAgora.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TagErrorCode implements ResponseCode {
    MINIMUM_TAGS_REQUIRED(HttpStatus.BAD_REQUEST, "태그는 최소 3개 이상 선택해야 합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}