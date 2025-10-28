package com.example.TripAgora.user.entity;

import com.example.TripAgora.common.entity.BaseEntity;
import com.example.TripAgora.guideProfile.entity.GuideProfile;
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

    @Column(nullable = false)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialType socialType;

    @Column(unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Column(nullable = false)
    private String imageUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTag> userTags = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private GuideProfile guideProfile;

    @Builder
    private User(String socialId, SocialType socialType, String imageUrl) {
        this.socialId = socialId;
        this.socialType = socialType;
        this.imageUrl = imageUrl;
        this.role = Role.TRAVELER;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateRole(Role role) {
        this.role = role;
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}