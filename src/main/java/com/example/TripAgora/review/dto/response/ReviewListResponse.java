package com.example.TripAgora.review.dto.response;

import java.util.List;

public record ReviewListResponse(
        List<ReviewResponse> reviews,
        boolean hasNext) {}