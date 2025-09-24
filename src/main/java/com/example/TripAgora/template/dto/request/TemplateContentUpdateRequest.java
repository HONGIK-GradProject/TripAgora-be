package com.example.TripAgora.template.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TemplateContentUpdateRequest(
        @NotBlank(message = "내용은 공백일 수 없습니다.")
        String content) {}