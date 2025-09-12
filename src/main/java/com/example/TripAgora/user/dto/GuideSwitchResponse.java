package com.example.TripAgora.user.dto;

public record GuideSwitchResponse(String accessToken, String refreshToken, Long guideProfileId) {
    // TODO: 가이드용 홈 화면 확정 후 수정
}