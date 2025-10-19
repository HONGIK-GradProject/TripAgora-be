package com.example.TripAgora.participation.controller;

import com.example.TripAgora.common.code.SuccessCode;
import com.example.TripAgora.common.response.ApiResponse;
import com.example.TripAgora.participation.dto.response.ParticipationResponse;
import com.example.TripAgora.participation.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/participation")
@RequiredArgsConstructor
public class ParticipationController {
    private final ParticipationService participationService;

    @PostMapping("/sessions/{sessionId}")
    public ApiResponse<ParticipationResponse> applyForSession(@AuthenticationPrincipal final long userId,
                                                              @PathVariable final long sessionId) {
        ParticipationResponse response = participationService.applyForSession(userId, sessionId);
        return ApiResponse.success(SuccessCode.OK, response);
    }

    @DeleteMapping("/sessions/{sessionId}")
    public ApiResponse<Void> cancelParticipation(@AuthenticationPrincipal final long userId,
                                                 @PathVariable final long sessionId) {
        participationService.cancelParticipation(userId, sessionId);
        return ApiResponse.success(SuccessCode.OK);
    }
}