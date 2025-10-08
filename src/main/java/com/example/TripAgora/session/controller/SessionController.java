package com.example.TripAgora.session.controller;

import com.example.TripAgora.common.code.SuccessCode;
import com.example.TripAgora.common.response.ApiResponse;
import com.example.TripAgora.session.dto.request.SessionCreateRequest;
import com.example.TripAgora.session.dto.response.SessionCreateResponse;
import com.example.TripAgora.session.service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}