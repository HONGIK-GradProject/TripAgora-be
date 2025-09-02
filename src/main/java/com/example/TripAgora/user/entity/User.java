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
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_social", columnNames = {"social_id", "social_type"})
        }
)

public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = false)
    private SocialType socialType;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "is_guide", nullable = false)
    private boolean isGuide = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTag> userTags = new ArrayList<>();

    @Builder
    private User(String socialId, SocialType socialType, String imageUrl) {
        this.socialId = socialId;
        this.socialType = socialType;
        this.imageUrl = imageUrl;
        this.role = Role.USER;
    }

    public enum Role {
        USER, ADMIN
    }
}