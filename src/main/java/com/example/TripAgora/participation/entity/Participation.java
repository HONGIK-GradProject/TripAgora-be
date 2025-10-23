package com.example.TripAgora.participation.entity;

import com.example.TripAgora.common.entity.BaseEntity;
import com.example.TripAgora.session.entity.Session;
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
@Table(name = "participation")
public class Participation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Participation(User user, Session session, Role role) {
        this.user = user;
        this.session = session;
        this.role = role;
    }
}