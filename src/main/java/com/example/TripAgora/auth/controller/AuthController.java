package com.example.TripAgora.auth.controller;

import com.example.TripAgora.auth.dto.ReissueRequest;
import com.example.TripAgora.auth.dto.ReissueResponse;
import com.example.TripAgora.auth.dto.SocialLoginRequest;
import com.example.TripAgora.auth.dto.SocialLoginResponse;
import com.example.TripAgora.auth.service.JWTService;
import com.example.TripAgora.auth.service.LoginService;
import com.example.TripAgora.common.code.SuccessCode;
import com.example.TripAgora.common.response.ApiResponse;
import com.example.TripAgora.user.entity.SocialType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LoginService loginService;
    private final JWTService jwtService;

    @PostMapping("/login/{socialType}")
    public ApiResponse<SocialLoginResponse> socialLogin(@PathVariable final SocialType socialType,
                                                        @Valid @RequestBody final SocialLoginRequest socialLoginRequest) {
        SocialLoginResponse SocialLoginResponse = loginService.socialLogin(socialLoginRequest, socialType);
        return ApiResponse.success(SuccessCode.OK, SocialLoginResponse);
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@AuthenticationPrincipal final long userId) {
        loginService.logout(userId);
        return ApiResponse.success(SuccessCode.NO_CONTENT);
    }

    @PostMapping("/reissue")
    public ApiResponse<ReissueResponse> reissue(@Valid @RequestBody final ReissueRequest reissueRequest) {
        ReissueResponse reissueResponse = jwtService.reissue(reissueRequest);
        return ApiResponse.success(SuccessCode.CREATED, reissueResponse);
    }
}