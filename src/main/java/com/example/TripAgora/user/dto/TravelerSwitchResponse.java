package com.example.TripAgora.user.dto;

public record TravelerSwitchResponse(String accessToken, String refreshToken, Long userId) {
    // TODO: 여행객용 홈 화면 확정 후 수정
}