package com.example.TripAgora.session.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record SessionDetailResponse(
        String title,
        String content,
        List<Long> regionIds,
        List<Long> tagIds,
        List<String> imageUrls,
        Integer maxParticipants,
        Integer currentParticipants,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,
        String status,
        List<SessionParticipantResponse> participants,
        String guideImageUrl,
        boolean isParticipating,
        boolean isMySession,
        boolean isInWishlist) {}