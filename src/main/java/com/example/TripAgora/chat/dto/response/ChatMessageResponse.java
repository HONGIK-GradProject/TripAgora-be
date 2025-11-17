package com.example.TripAgora.chat.dto.response;

import com.example.TripAgora.user.entity.Role;

import java.time.LocalDateTime;

public record ChatMessageResponse(
        Long chatMessageId,
        Long roomId,
        Long senderId,
        String senderNickname,
        String senderImageUrl,
        String content,
        LocalDateTime sentAt,
        Role senderRole) {}