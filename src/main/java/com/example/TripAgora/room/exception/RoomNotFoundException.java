package com.example.TripAgora.room.exception;

import com.example.TripAgora.common.code.RoomErrorCode;

public class RoomNotFoundException extends RoomException {
    public RoomNotFoundException() {
        super(RoomErrorCode.ROOM_NOT_FOUND);
    }
}