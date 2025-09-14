package com.example.TripAgora.user.controller;

import com.example.TripAgora.common.code.SuccessCode;
import com.example.TripAgora.common.response.ApiResponse;
import com.example.TripAgora.guideProfile.service.GuideProfileService;
import com.example.TripAgora.user.dto.*;
import com.example.TripAgora.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final GuideProfileService guideProfileService;

    @PatchMapping("/me/nickname")
    public ApiResponse<NicknameUpdateResponse> updateNickname(@AuthenticationPrincipal final long userId,
                                                              @RequestBody @Valid final NicknameUpdateRequest request) {
        NicknameUpdateResponse response = userService.updateNickname(userId, request);
        return ApiResponse.success(SuccessCode.OK, response);
    }

    @PatchMapping("/me/tags")
    public ApiResponse<TagUpdateResponse> updateTags(@AuthenticationPrincipal final long userId,
                                                     @RequestBody @Valid final TagUpdateRequest request) {
        TagUpdateResponse response = userService.updateTags(userId, request);
        return ApiResponse.success(SuccessCode.OK, response);
    }

    @PostMapping("/me/switch-to-guide")
    public ApiResponse<GuideSwitchResponse> switchToGuide(@AuthenticationPrincipal final long userId) {
        GuideSwitchResponse response = guideProfileService.switchToGuide(userId);
        return ApiResponse.success(SuccessCode.OK, response);
    }

    @PostMapping("/me/switch-to-traveler")
    public ApiResponse<TravelerSwitchResponse> switchToTraveler(@AuthenticationPrincipal final long userId) {
        TravelerSwitchResponse response = guideProfileService.switchToTraveler(userId);
        return ApiResponse.success(SuccessCode.OK, response);
    }
}