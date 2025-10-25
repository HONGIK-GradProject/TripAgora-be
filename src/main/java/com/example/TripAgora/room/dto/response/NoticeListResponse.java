package com.example.TripAgora.room.dto.response;

import java.util.List;

public record NoticeListResponse(
        List<NoticeSummaryResponse> notices,
        boolean hasNext) {}