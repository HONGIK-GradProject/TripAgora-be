package com.example.TripAgora.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ParticipationErrorCode implements ResponseCode {
    PARTICIPATION_NOT_FOUND(HttpStatus.NOT_FOUND, "여행 참여를 찾을 수 없습니다."),
    ALREADY_PARTICIPATING(HttpStatus.CONFLICT, "이미 해당 세션에 참여 신청을 했습니다."),
    CANNOT_PARTICIPATE_IN_OWN_SESSION(HttpStatus.BAD_REQUEST, "자신이 개설한 세션에는 참여할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}