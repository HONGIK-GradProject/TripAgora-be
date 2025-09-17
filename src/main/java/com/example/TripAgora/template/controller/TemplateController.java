package com.example.TripAgora.template.controller;

import com.example.TripAgora.common.code.SuccessCode;
import com.example.TripAgora.common.response.ApiResponse;
import com.example.TripAgora.template.dto.request.TemplateItineraryUpdateRequest;
import com.example.TripAgora.template.dto.request.TemplateRegionUpdateRequest;
import com.example.TripAgora.template.dto.request.TemplateTagUpdateRequest;
import com.example.TripAgora.template.dto.request.TemplateUpdateRequest;
import com.example.TripAgora.template.dto.response.TemplateCreateResponse;
import com.example.TripAgora.template.dto.response.TemplateRegionUpdateResponse;
import com.example.TripAgora.template.dto.response.TemplateTagUpdateResponse;
import com.example.TripAgora.template.service.TemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{templateId}")
    public ApiResponse<Void> updateTemplate(@AuthenticationPrincipal final long userId,
                                            @PathVariable final long templateId,
                                            @RequestBody @Valid final TemplateUpdateRequest request) {
        templateService.updateTemplate(userId, templateId, request);
        return ApiResponse.success(SuccessCode.OK);
    }

    @PatchMapping("/{templateId}/tags")
    public ApiResponse<TemplateTagUpdateResponse> updateTags(@AuthenticationPrincipal final long userId,
                                                             @PathVariable final long templateId,
                                                             @RequestBody @Valid final TemplateTagUpdateRequest request) {
        TemplateTagUpdateResponse response = templateService.updateTags(userId, templateId, request);
        return ApiResponse.success(SuccessCode.OK, response);
    }

    @PatchMapping("/{templateId}/regions")
    public ApiResponse<TemplateRegionUpdateResponse> updateRegions(@AuthenticationPrincipal final long userId,
                                                                   @PathVariable final long templateId,
                                                                   @RequestBody @Valid final TemplateRegionUpdateRequest request) {
        TemplateRegionUpdateResponse response = templateService.updateRegions(userId, templateId, request);
        return ApiResponse.success(SuccessCode.OK, response);
    }

    @PutMapping("/{templateId}/itineraries")
    public ApiResponse<Void> updateItineraries(@AuthenticationPrincipal final long userId,
                                               @PathVariable final long templateId,
                                               @RequestBody @Valid final TemplateItineraryUpdateRequest request) {
        templateService.updateItineraries(userId, templateId, request);
        return ApiResponse.success(SuccessCode.OK);
    }
}