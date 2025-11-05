package com.example.TripAgora.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TemplateErrorCode implements ResponseCode {
    TEMPLATE_NOT_FOUND(HttpStatus.NOT_FOUND, "템플릿을 찾을 수 없습니다."),
    ITINERARY_DAY_SEQUENCE_INVALID(HttpStatus.BAD_REQUEST, "일정은 day1부터 순차적으로 존재해야합니다."),
    TEMPLATE_HAS_ACTIVE_SESSIONS(HttpStatus.CONFLICT, "해당 템플릿으로 개설된 세션이 존재하여 수정 또는 삭제할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}