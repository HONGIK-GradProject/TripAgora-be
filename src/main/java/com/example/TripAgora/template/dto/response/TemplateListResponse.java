package com.example.TripAgora.template.dto.response;

import java.util.List;

public record TemplateListResponse(
        List<TemplateSummaryResponse> templates,
        boolean hasNext) {}