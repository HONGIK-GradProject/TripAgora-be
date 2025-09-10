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
    public ApiResponse<NicknameResponse> updateNickname(@AuthenticationPrincipal final long userId,
                                                        @RequestBody @Valid final NicknameRequest nicknameRequest) {
        return ApiResponse.success(SuccessCode.OK, userService.updateNickname(userId, nicknameRequest.nickname()));
    }

    @PatchMapping("/me/tags")
    public ApiResponse<TagResponse> updateTags(@AuthenticationPrincipal final long userId,
                                                 @RequestBody @Valid final TagRequest tagRequest) {
        return ApiResponse.success(SuccessCode.OK, userService.updateTags(userId, tagRequest.tagIds()));
    }

    @PostMapping("/me/switch-to-guide")
    public ApiResponse<GuideSwitchResponse> switchToGuide(@AuthenticationPrincipal final long userId) {
        return ApiResponse.success(SuccessCode.OK, guideProfileService.switchToGuide(userId)
        );
    }

    @PostMapping("/me/switch-to-traveler")
    public ApiResponse<TravelerSwitchResponse> switchToTraveler(@AuthenticationPrincipal final long userId) {
        return ApiResponse.success(SuccessCode.OK, guideProfileService.switchToTraveler(userId)
        );
    }
}