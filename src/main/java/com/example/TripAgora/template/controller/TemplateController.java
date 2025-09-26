package com.example.TripAgora.template.controller;

import com.example.TripAgora.common.code.SuccessCode;
import com.example.TripAgora.common.response.ApiResponse;
import com.example.TripAgora.template.dto.request.*;
import com.example.TripAgora.template.dto.response.*;
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

    @GetMapping("/{templateId}")
    public ApiResponse<TemplateDetailResponse> getTemplate(@AuthenticationPrincipal final long userId,
                                                           @PathVariable final long templateId) {
        TemplateDetailResponse response = templateService.getTemplate(userId, templateId);
        return ApiResponse.success(SuccessCode.OK, response);
    }

    @GetMapping("/{templateId}/itineraries")
    public ApiResponse<TemplateItinerariesResponse> getItineraries(@AuthenticationPrincipal final long userId,
                                                                   @PathVariable final long templateId) {
        TemplateItinerariesResponse response = templateService.getItineraries(userId, templateId);
        return ApiResponse.success(SuccessCode.OK, response);
    }

    @PostMapping
    public ApiResponse<TemplateCreateResponse> createDraftTemplate(@AuthenticationPrincipal final long userId) {
        TemplateCreateResponse response = templateService.createDraftTemplate(userId);
        return ApiResponse.success(SuccessCode.CREATED, response);
    }

    @PutMapping("/{templateId}")
    public ApiResponse<Void> saveTemplate(@AuthenticationPrincipal final long userId,
                                          @PathVariable final long templateId,
                                          @RequestBody @Valid final TemplateSaveRequest request) {
        templateService.saveTemplate(userId, templateId, request);
        return ApiResponse.success(SuccessCode.OK);
    }

    @PatchMapping("/{templateId}/title")
    public ApiResponse<TemplateTitleUpdateResponse> updateTitle(@AuthenticationPrincipal final long userId,
                                                                @PathVariable final long templateId,
                                                                @RequestBody @Valid final TemplateTitleUpdateRequest request) {
        TemplateTitleUpdateResponse response = templateService.updateTitle(userId, templateId, request);
        return ApiResponse.success(SuccessCode.OK, response);
    }

    @PatchMapping("/{templateId}/content")
    public ApiResponse<TemplateContentUpdateResponse> updateContent(@AuthenticationPrincipal final long userId,
                                                                    @PathVariable final long templateId,
                                                                    @RequestBody @Valid final TemplateContentUpdateRequest request) {
        TemplateContentUpdateResponse response = templateService.updateContent(userId, templateId, request);
        return ApiResponse.success(SuccessCode.OK, response);
    }

    @PatchMapping("/{templateId}/images")
    public ApiResponse<Void> updateImages(@AuthenticationPrincipal final long userId,
                                          @PathVariable final long templateId,
                                          @RequestBody @Valid final TemplateImageUpdateRequest request) {
        templateService.updateImages(userId, templateId, request);
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