package com.example.TripAgora.template.dto;

import jakarta.validation.constraints.Size;

import java.util.List;

public record TemplateTagUpdateRequest(
        @Size(max = 5, message = "최대 5개까지 태그를 선택할 수 있습니다.")
        List<Long> tagIds) {}