package com.example.TripAgora.session.dto.response;

import java.util.List;

public record SessionListResponse(
        List<SessionSummaryResponse> sessions,
        boolean hasNext) {}