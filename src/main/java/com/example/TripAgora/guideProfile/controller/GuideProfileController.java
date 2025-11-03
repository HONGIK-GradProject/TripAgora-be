package com.example.TripAgora.guideProfile.controller;

import com.example.TripAgora.common.code.SuccessCode;
import com.example.TripAgora.common.response.ApiResponse;
import com.example.TripAgora.guideProfile.dto.response.GuideProfileDetailResponse;
import com.example.TripAgora.guideProfile.service.GuideProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guide-profiles")
@RequiredArgsConstructor
public class GuideProfileController {
    private final GuideProfileService guideProfileService;

    @GetMapping("/{guideProfileId}")
    public ApiResponse<GuideProfileDetailResponse> getGuideProfileDetails(@PathVariable final long guideProfileId,
                                                                          @PageableDefault(size = 10) Pageable pageable) {
        GuideProfileDetailResponse response = guideProfileService.getGuideProfileDetails(guideProfileId, pageable);
        return ApiResponse.success(SuccessCode.OK, response);
    }
}