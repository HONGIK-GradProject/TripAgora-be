package com.example.TripAgora.wishlist.exception;

import com.example.TripAgora.common.code.WishlistErrorCode;

public class AlreadyInWishlistException extends WishlistException {
    public AlreadyInWishlistException() {
        super(WishlistErrorCode.ALREADY_IN_WISHLIST);
    }
}