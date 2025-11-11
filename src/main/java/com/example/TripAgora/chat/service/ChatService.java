package com.example.TripAgora.chat.service;

import com.example.TripAgora.chat.dto.request.ChatMessageRequest;
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
import com.example.TripAgora.user.exception.UserNotFoundException;
import com.example.TripAgora.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ParticipationRepository participationRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public ChatMessageResponse processAndSaveMessage(Long userId, Long roomId, ChatMessageRequest request) {
        // 구독과는 별개로 메시지를 전송할 때도 검증을 해야함 ex) connect만 성공하고 subscribe를 하지 않은 채 메시지를 보낼 수도 있으므로
        // 인터셉터는 connect, subscribe 시에만 검증을 한다

        User sender = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Room room = roomRepository.findById(roomId).orElseThrow(RoomNotFoundException::new);

        Long sessionId = room.getSession().getId();
        Participation participation = participationRepository.findByUser_IdAndSession_Id(userId, sessionId)
                .orElseThrow(ParticipationNotFoundException::new);

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
}