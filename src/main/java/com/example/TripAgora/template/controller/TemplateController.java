package com.example.TripAgora.template.controller;

import com.example.TripAgora.common.code.SuccessCode;
import com.example.TripAgora.common.response.ApiResponse;
import com.example.TripAgora.template.dto.TemplateCreateResponse;
import com.example.TripAgora.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/templates")
@RequiredArgsConstructor
public class TemplateController {
    private final TemplateService templateService;

    @PostMapping
    public ApiResponse<TemplateCreateResponse> createDraftTemplate(@AuthenticationPrincipal final long userId) {
        TemplateCreateResponse response = templateService.createDraftTemplate(userId);
        return ApiResponse.success(SuccessCode.CREATED, response);
    }
}