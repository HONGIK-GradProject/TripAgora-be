package com.example.TripAgora.chat.entity;

import com.example.TripAgora.common.entity.BaseEntity;
import com.example.TripAgora.room.entity.Room;
import com.example.TripAgora.user.entity.Role;
import com.example.TripAgora.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chat_message")
public class ChatMessage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private Role senderRole;

    @Builder
    private ChatMessage(Room room, User sender, String content, Role senderRole) {
        this.room = room;
        this.sender = sender;
        this.content = content;
        this.senderRole = senderRole;
    }
}