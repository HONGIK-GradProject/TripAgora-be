package com.example.TripAgora.wishlist.exception;

import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.exception.ApplicationException;

public class WishlistException extends ApplicationException {
    public WishlistException(ResponseCode errorCode) {
        super(errorCode);
    }
}