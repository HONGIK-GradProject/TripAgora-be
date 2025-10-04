package com.example.TripAgora.template.dto.response;

import java.time.LocalTime;

public record ItineraryItemResponse(
        Long id,
        Integer day,
        String title,
        String content,
        LocalTime startTime,
        Double latitude,
        Double longitude) {}