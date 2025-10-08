package com.example.TripAgora.session.entity;

import com.example.TripAgora.common.entity.BaseEntity;
import com.example.TripAgora.template.entity.TemplateItinerary;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "session_itinerary")
public class SessionItinerary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

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
    private SessionItinerary(Session session, TemplateItinerary templateItinerary) {
        this.session = session;
        this.day = templateItinerary.getDay();
        this.title = templateItinerary.getTitle();
        this.content = templateItinerary.getContent();
        this.startTime = templateItinerary.getStartTime();
        this.latitude = templateItinerary.getLatitude();
        this.longitude = templateItinerary.getLongitude();
    }
}