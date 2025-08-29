package com.example.TripAgora.user.entity;

import com.example.TripAgora.common.entity.BaseEntity;
import com.example.TripAgora.tag.entity.UserTag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseEntity {
    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_provider", nullable = false)
    private SocialProvider socialProvider;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "is_guide", nullable = false)
    private boolean isGuide = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTag> userTags = new ArrayList<>();

    @Builder
    private User(String socialId, SocialProvider socialProvider, String nickname, String imageUrl, boolean isGuide) {
        this.socialId = socialId;
        this.socialProvider = socialProvider;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.isGuide = isGuide;
    }

    public enum SocialProvider {
        KAKAO, NAVER, GOOGLE
    }
}