package com.example.TripAgora.template.entity;

import com.example.TripAgora.common.entity.BaseEntity;
import com.example.TripAgora.guideProfile.entity.GuideProfile;
import com.example.TripAgora.region.entity.TemplateRegion;
import com.example.TripAgora.tag.entity.TemplateTag;
import com.example.TripAgora.template.dto.request.ItineraryItemRequest;
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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private Double totalAvgRating = 0.0;

    @Column
    private Integer totalReviewCount = 0;

    @OrderBy("day ASC , startTime ASC ")
    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateItinerary> templateItineraries = new ArrayList<>();

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateTag> templateTags = new ArrayList<>();

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateImage> templateImages = new ArrayList<>();

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateRegion> templateRegions = new ArrayList<>();

    @Builder
    private Template(GuideProfile guideProfile, String title, String content) {
        this.guideProfile = guideProfile;
        this.title = "";
        this.content = "";
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
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

    public void clearItineraries() {
        this.templateItineraries.clear();
    }

    public void addItinerary(ItineraryItemRequest item) {
        TemplateItinerary itinerary = TemplateItinerary.builder()
                .template(this)
                .day(item.day())
                .title(item.title())
                .content(item.content())
                .startTime(item.startTime())
                .latitude(item.latitude())
                .longitude(item.longitude())
                .build();
        this.templateItineraries.add(itinerary);
    }
}