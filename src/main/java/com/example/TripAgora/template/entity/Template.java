package com.example.TripAgora.template.entity;

import com.example.TripAgora.common.entity.BaseEntity;
import com.example.TripAgora.guideProfile.entity.GuideProfile;
import com.example.TripAgora.region.entity.TemplateRegion;
import com.example.TripAgora.tag.entity.TemplateTag;
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
@Table(name = "template")
public class Template extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guide_profile_id", nullable = false)
    private GuideProfile guideProfile;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "total_avg_rating")
    private Double totalAvgRating = 0.0;

    @Column(name = "total_review_count")
    private Integer totalReviewCount = 0;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateItinerary> templateItineraries = new ArrayList<>();

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateTag> templateTags = new ArrayList<>();

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateImage> templateImages = new ArrayList<>();

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateRegion> templateRegions = new ArrayList<>();

//    @OneToMany(mappedBy = "tripTemplate", cascade = CascadeType.ALL)
//    private List<TripSession> tripSessions = new ArrayList<>();

    @Builder
    private Template(GuideProfile guideProfile, String title, String content) {
        this.guideProfile = guideProfile;
        this.title = "";
        this.content = "";
    }

    public void updateInfo(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void clearImages() {
        this.templateImages.clear();
    }

    public void addImage(String imageUrl) {
        TemplateImage templateImage = TemplateImage.builder()
                .template(this)
                .imageUrl(imageUrl)
                .displayOrder(this.templateImages.size() + 1)
                .build();
        this.templateImages.add(templateImage);
    }
}