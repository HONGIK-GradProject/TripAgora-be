package com.example.TripAgora.room.dto.response;

import java.time.LocalDateTime;

public record NoticeSummaryResponse(
        Long noticeId,
        String title,
        LocalDateTime createdAt) {}