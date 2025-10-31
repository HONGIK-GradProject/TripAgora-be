package com.example.TripAgora.wishlist.exception;

import com.example.TripAgora.common.code.WishlistErrorCode;

public class WishlistNotFoundException extends WishlistException {
    public WishlistNotFoundException() {
        super(WishlistErrorCode.WISHLIST_NOT_FOUND);
    }
}