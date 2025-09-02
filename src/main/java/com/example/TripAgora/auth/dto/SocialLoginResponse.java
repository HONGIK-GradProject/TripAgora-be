package com.example.TripAgora.auth.dto;

public record SocialLoginResponse(String accessToken, String refreshToken, boolean isNewUser) {
}