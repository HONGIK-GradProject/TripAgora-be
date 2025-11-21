package com.example.TripAgora.stomp.controller;

import com.example.TripAgora.chat.dto.request.ChatMessageRequest;
import com.example.TripAgora.chat.dto.response.ChatMessageResponse;
import com.example.TripAgora.chat.service.ChatService;
import com.example.TripAgora.location.dto.request.LocationUpdateRequest;
import com.example.TripAgora.location.dto.response.LocationResponse;
import com.example.TripAgora.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompController {
    private final ChatService chatService;
    private final LocationService locationService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/room/{roomId}/chat")
    public void sendMessage(@DestinationVariable(value = "roomId") Long roomId,
                            ChatMessageRequest request,
                            Principal principal) {
        Long userId = Long.valueOf(principal.getName());
        ChatMessageResponse response = chatService.processAndSaveMessage(userId, roomId, request);
        messagingTemplate.convertAndSend("/topic/room/" + roomId + "/chat", response);
    }


}