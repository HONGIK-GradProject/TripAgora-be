package com.example.TripAgora.template.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TemplateSaveRequest(
        @NotBlank(message = "제목은 공백일 수 없습니다.")
        String title,

        @NotBlank(message = "내용은 공백일 수 없습니다.")
        String content,

        List<String> imageUrls) {}