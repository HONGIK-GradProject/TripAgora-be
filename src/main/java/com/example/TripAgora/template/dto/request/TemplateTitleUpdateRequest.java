package com.example.TripAgora.template.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TemplateTitleUpdateRequest(
        @NotBlank(message = "제목은 공백일 수 없습니다.")
        String title) {}