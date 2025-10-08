package com.example.TripAgora.session.entity;

import com.example.TripAgora.common.entity.BaseEntity;
import com.example.TripAgora.template.entity.Template;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "session")
public class Session extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", nullable = false)
    private Template template; // 원본이 된 템플릿

    @Column(nullable = false)
    private Integer maxParticipants;

    @Column(nullable = false)
    private Integer currentParticipants = 0;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionStatus status;

    @OrderBy("day ASC , startTime ASC ")
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionItinerary> sessionItineraries = new ArrayList<>();

    // ✨ 템플릿 정보를 바탕으로 세션을 생성하는 빌더
    @Builder
    private Session(Template template, Integer maxParticipants, LocalDate startDate, LocalDate endDate) {
        this.template = template;
        this.maxParticipants = maxParticipants;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = SessionStatus.RECRUITING;
    }

    public void addItinerary(SessionItinerary itinerary) {
        this.sessionItineraries.add(itinerary);
    }
}