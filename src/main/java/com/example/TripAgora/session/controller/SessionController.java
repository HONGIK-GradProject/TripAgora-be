package com.example.TripAgora.session.controller;

import com.example.TripAgora.common.code.SuccessCode;
import com.example.TripAgora.common.response.ApiResponse;
import com.example.TripAgora.session.dto.request.SessionCreateRequest;
import com.example.TripAgora.session.dto.request.SessionUpdateRequest;
import com.example.TripAgora.session.dto.response.SessionCreateResponse;
import com.example.TripAgora.session.dto.response.SessionDetailResponse;
import com.example.TripAgora.session.dto.response.SessionItinerariesResponse;
import com.example.TripAgora.session.dto.response.SessionListResponse;
import com.example.TripAgora.session.entity.SessionStatus;
import com.example.TripAgora.session.service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;

    @PostMapping
    public ApiResponse<SessionCreateResponse> createSession(@AuthenticationPrincipal final long userId,
                                                            @RequestBody @Valid final SessionCreateRequest request) {
        SessionCreateResponse response = sessionService.createSession(userId, request);
        return ApiResponse.success(SuccessCode.CREATED, response);
    }

    @GetMapping("/{sessionId}")
    public ApiResponse<SessionDetailResponse> getSessionDetails(@PathVariable final long sessionId) {
        SessionDetailResponse response = sessionService.getSessionDetails(sessionId);
        return ApiResponse.success(SuccessCode.OK, response);
    }

    @PatchMapping("/{sessionId}")
    public ApiResponse<Void> updateSession(@AuthenticationPrincipal final long userId,
                                           @PathVariable final long sessionId,
                                           @RequestBody @Valid final SessionUpdateRequest request) {
        sessionService.updateSession(userId, sessionId, request);
        return ApiResponse.success(SuccessCode.OK);
    }

    @DeleteMapping("/{sessionId}")
    public ApiResponse<Void> deleteSession(@AuthenticationPrincipal final long userId,
                                           @PathVariable final long sessionId) {
        sessionService.deleteSession(userId, sessionId);
        return ApiResponse.success(SuccessCode.OK);
    }

    @GetMapping("/{sessionId}/itineraries")
    public ApiResponse<SessionItinerariesResponse> getItineraries(@AuthenticationPrincipal final long userId,
                                                                   @PathVariable final long sessionId) {
        SessionItinerariesResponse response = sessionService.getItineraries(userId, sessionId);
        return ApiResponse.success(SuccessCode.OK, response);
    }

    @GetMapping("/my")
    public ApiResponse<SessionListResponse> getMySessions(@AuthenticationPrincipal final long userId,
                                                          @RequestParam(required = false) List<SessionStatus> statuses,
                                                          @PageableDefault(size = 10) Pageable pageable) {
        SessionListResponse response = sessionService.getMySessions(userId, statuses, pageable);
        return ApiResponse.success(SuccessCode.OK, response);
    }

    @PostMapping("/{sessionId}/close")
    public ApiResponse<Void> closeRecruitment(@AuthenticationPrincipal final long userId,
                                              @PathVariable final long sessionId) {
        sessionService.closeRecruitment(userId, sessionId);
        return ApiResponse.success(SuccessCode.OK);
    }
}