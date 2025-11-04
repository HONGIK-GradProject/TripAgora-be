package com.example.TripAgora.guideProfile.dto.response;

import com.example.TripAgora.session.dto.response.SessionListResponse;

import java.util.List;

public record GuideProfileDetailResponse(
        String nickname,
        String imageUrl,
        String bio,
        List<Long> tags,
        List<PortfolioResponse> portfolios,
        SessionListResponse SessionList) {}