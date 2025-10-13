package com.example.TripAgora.template.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public record TemplateItineraryItemResponse(
        Long id,
        Integer day,
        String title,
        String content,
        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime startTime,
        Double latitude,
        Double longitude) {}