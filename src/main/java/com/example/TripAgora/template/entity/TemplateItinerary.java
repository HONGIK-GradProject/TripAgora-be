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

    @Column(name = "day", nullable = false)
    private Integer day;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "latitude", nullable = false)
    private Double latitude;  // 위도

    @Column(name = "longitude", nullable = false)
    private Double longitude; // 경도

    @Builder
    private TemplateItinerary(Template template, Integer day, String title, String content, LocalTime startTime, LocalTime endTime, Integer displayOrder, String placeName, Double latitude, Double longitude) {
        this.template = template;
        this.day = day;
        this.title = title;
        this.content = content;
        this.startTime = startTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}