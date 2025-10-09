package com.example.TripAgora.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SessionErrorCode implements ResponseCode{
    SESSION_NOT_FOUND(HttpStatus.NOT_FOUND, "세션을 찾을 수 없습니다."),
    SESSION_NOT_RECRUITING(HttpStatus.BAD_REQUEST, "인원을 모집중인 세션이 아닙니다."),
    INVALID_MAX_PARTICIPANTS(HttpStatus.BAD_REQUEST, "현재 신청 인원보다 적은 수로 정원을 수정할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}