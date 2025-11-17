package com.example.TripAgora.session.dto.response;

import java.util.List;

public record CompletedSessionListResponse(
        List<CompletedSessionResponse> sessions,
        boolean hasNext) {}