package com.example.TripAgora.template.dto.response;

import java.util.List;

public record TemplateDetailResponse(String title,
                                     String content,
                                     List<String> regionNames,
                                     List<String> tagNames,
                                     List<String> imageUrls) {}