package com.example.TripAgora.chat.dto.response;

import java.time.LocalDateTime;

public record ChatMessageResponse(
        Long roomId,
        Long senderId,
        String senderNickname,
        String senderImageUrl,
        String content,
        LocalDateTime sentAt) {}