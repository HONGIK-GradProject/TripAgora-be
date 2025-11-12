package com.example.TripAgora.chat.service;

import com.example.TripAgora.chat.dto.request.ChatMessageRequest;
import com.example.TripAgora.chat.dto.response.ChatHistoryResponse;
import com.example.TripAgora.chat.dto.response.ChatMessageResponse;
import com.example.TripAgora.chat.entity.ChatMessage;
import com.example.TripAgora.chat.repository.ChatMessageRepository;
import com.example.TripAgora.participation.entity.Participation;
import com.example.TripAgora.participation.exception.ParticipationNotFoundException;
import com.example.TripAgora.participation.repository.ParticipationRepository;
import com.example.TripAgora.room.entity.Room;
import com.example.TripAgora.room.exception.RoomNotFoundException;
import com.example.TripAgora.room.repository.RoomRepository;
import com.example.TripAgora.user.entity.User;
import com.example.TripAgora.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ParticipationRepository participationRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public ChatMessageResponse processAndSaveMessage(Long userId, Long roomId, ChatMessageRequest request) {
        Participation participation = checkAndGetParticipation(userId, roomId);

        User sender = participation.getUser();
        Room room = participation.getSession().getRoom();

        ChatMessage chatMessage = ChatMessage.builder()
                .room(room)
                .sender(sender)
                .content(request.content())
                .build();

        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

        return new ChatMessageResponse(
                roomId,
                sender.getId(),
                sender.getNickname(),
                sender.getImageUrl(),
                savedMessage.getContent(),
                savedMessage.getCreatedAt()
        );
    }

    @Transactional(readOnly = true)
    public ChatHistoryResponse getChatHistory(Long userId, Long roomId, Pageable pageable) {
        checkAndGetParticipation(userId, roomId);

        Slice<ChatMessage> messages = chatMessageRepository.findByRoom_Id(roomId, pageable);

        List<ChatMessageResponse> chatMessages = messages.getContent().stream()
                .map(msg -> new ChatMessageResponse(
                        msg.getRoom().getId(),
                        msg.getSender().getId(),
                        msg.getSender().getNickname(),
                        msg.getSender().getImageUrl(),
                        msg.getContent(),
                        msg.getCreatedAt()
                ))
                .toList();

        return new ChatHistoryResponse(chatMessages, messages.hasNext());
    }

    private Participation checkAndGetParticipation(Long userId, Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(RoomNotFoundException::new);
        Long sessionId = room.getSession().getId();

        return participationRepository.findByUser_IdAndSession_Id(userId, sessionId)
                .orElseThrow(ParticipationNotFoundException::new);
    }
}