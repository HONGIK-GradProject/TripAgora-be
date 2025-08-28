package com.example.TripAgora.user.entity;

import com.example.TripAgora.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseEntity {
    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 소셜로그인 id (NOT NULL)
    @Column(name = "social_id", nullable = false)
    private String socialId;

    // 소셜로그인 제공자 (ENUM in DB, enum으로 매핑)
    @Enumerated(EnumType.STRING)
    @Column(name = "social_provider", nullable = false)
    private SocialProvider socialProvider;

    // 닉네임 (NOT NULL)
    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    // 프로필 이미지 URL (VARCHAR(2083), NOT NULL)
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    // 현재 가이드 역할 여부 (BOOLEAN NOT NULL DEFAULT FALSE)
    @Column(name = "is_guide", nullable = false)
    private boolean isGuide = false;

    @Builder
    private User(String socialId, SocialProvider socialProvider, String nickname, String imageUrl, boolean isGuide) {
        this.socialId = socialId;
        this.socialProvider = socialProvider;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.isGuide = isGuide;
    }

    public enum SocialProvider {
        KAKAO, NAVER, GOOGLE // 필요에 맞게 확장
    }
}