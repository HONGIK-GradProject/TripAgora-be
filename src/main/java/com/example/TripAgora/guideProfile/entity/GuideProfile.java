package com.example.TripAgora.guideProfile.entity;

import com.example.TripAgora.common.entity.BaseEntity;
import com.example.TripAgora.template.entity.Template;
import com.example.TripAgora.user.entity.User;
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
@Table(name = "guide_profile")
public class GuideProfile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "total_avg_rating")
    private Double totalAvgRating = 0.0;

    @Column(name = "total_review_count")
    private Integer totalReviewCount = 0;

    @OneToMany(mappedBy = "guideProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuidePortfolio> portfolios = new ArrayList<>();

    @OneToMany(mappedBy = "guideProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Template> templates = new ArrayList<>();

    @Builder
    private GuideProfile (User user) {
        this.user = user;
    }

    public void updateBio(String bio) {
        this.bio = bio;
    }

    public void updateRating(double newAverage, int newReviewCount) {
        this.totalAvgRating = newAverage;
        this.totalReviewCount = newReviewCount;
    }
}