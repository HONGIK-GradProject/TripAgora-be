package com.example.TripAgora.template.entity;

import com.example.TripAgora.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "template_itinerary")
public class TemplateItinerary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    @Column(nullable = false)
    private Integer day;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Builder
    private TemplateItinerary(Template template, Integer day, String title, String content, LocalTime startTime, Double latitude, Double longitude) {
        this.template = template;
        this.day = day;
        this.title = title;
        this.content = content;
        this.startTime = startTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}