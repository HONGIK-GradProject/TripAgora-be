package com.example.TripAgora.chat.controller;

import com.example.TripAgora.chat.dto.response.ChatHistoryResponse;
import com.example.TripAgora.chat.service.ChatService;
import com.example.TripAgora.common.code.SuccessCode;
import com.example.TripAgora.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/room/{roomId}/messages")
    public ApiResponse<ChatHistoryResponse> getChatMessages(@AuthenticationPrincipal final long userId,
                                                            @PathVariable Long roomId,
                                                            @PageableDefault(size = 30, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        ChatHistoryResponse response = chatService.getChatHistory(userId, roomId, pageable);
        return ApiResponse.success(SuccessCode.OK, response);
    }
}