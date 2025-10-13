package com.example.TripAgora.session.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public record SessionItineraryItemResponse(
        Long id,
        Integer day,
        String title,
        String content,
        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime startTime,
        Double latitude,
        Double longitude) {}