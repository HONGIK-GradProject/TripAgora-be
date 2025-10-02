package com.example.TripAgora.template.dto.response;

import java.util.List;

public record TemplateSummaryResponse(
        Long templateId,
        String title,
        String firstImageUrl,
        List<Long> regionIds) {}