package com.example.TripAgora.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RoomErrorCode implements ResponseCode{
    ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "룸을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}