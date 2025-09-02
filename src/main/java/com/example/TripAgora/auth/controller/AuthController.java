package com.example.TripAgora.auth.controller;

import com.example.TripAgora.auth.dto.SocialLoginRequest;
import com.example.TripAgora.auth.dto.SocialLoginResponse;
import com.example.TripAgora.auth.service.JWTService;
import com.example.TripAgora.auth.service.LoginService;
import com.example.TripAgora.common.code.SuccessCode;
import com.example.TripAgora.common.response.ApiResponse;
import com.example.TripAgora.user.entity.SocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LoginService loginService;
    private final JWTService jwtService;

    @PostMapping("/login/{socialType}")
    public ResponseEntity<ApiResponse<SocialLoginResponse>> socialLogin(@PathVariable SocialType socialType, @RequestBody SocialLoginRequest socialLoginRequest) {
        SocialLoginResponse SocialLoginResponse = loginService.socialLogin(socialLoginRequest, socialType);
        return ResponseEntity.ok(ApiResponse.success(SuccessCode.OK, SocialLoginResponse));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@AuthenticationPrincipal Long userId) {
        loginService.logout(userId);
        return ResponseEntity.ok(ApiResponse.success(SuccessCode.OK));
    }
}