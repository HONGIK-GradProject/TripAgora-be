package com.example.TripAgora.review.dto.response;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long reviewId,
        String authorNickname,
        String authorProfileImage,
        String content,
        Integer rating,
        LocalDateTime createdAt) {}