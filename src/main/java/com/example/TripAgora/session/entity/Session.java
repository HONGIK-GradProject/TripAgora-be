package com.example.TripAgora.session.entity;

import com.example.TripAgora.common.entity.BaseEntity;
import com.example.TripAgora.template.entity.Template;
import com.example.TripAgora.template.entity.TemplateItinerary;
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

    @Builder
    private Session(Template template, Integer maxParticipants, LocalDate startDate) {
        this.template = template;
        this.maxParticipants = maxParticipants;
        this.startDate = startDate;
        this.endDate = calculateEndDate(template, startDate);
        this.status = SessionStatus.RECRUITING;
    }

    public void addItinerary(SessionItinerary itinerary) {
        this.sessionItineraries.add(itinerary);
    }

    public void updateInfo(Integer maxParticipants, LocalDate startDate) {
        this.maxParticipants = maxParticipants;
        this.startDate = startDate;
        this.endDate = calculateEndDate(this.template, startDate);
    }

    // TODO: 추후 여행 참여 로직 작성 시 수정
    public void updateStatus(SessionStatus newStatus) {
        this.status = newStatus;
    }

    // TODO: 추후 여행 참여 로직 작성 시 수정
    public void increaseParticipant() {
        if (this.currentParticipants >= this.maxParticipants) {
            throw new IllegalStateException("모집 정원이 가득 찼습니다.");
        }

        this.currentParticipants++;
    }

    // TODO: 추후 여행 참여 로직 작성 시 수정
    public void decreaseParticipant() {
        if (this.currentParticipants <= 0) {
            throw new IllegalStateException("현재 참가자가 없습니다.");
        }

        this.currentParticipants--;
    }

    public static LocalDate calculateEndDate(Template template, LocalDate startDate) {
        int maxDay = template.getTemplateItineraries().stream()
                .mapToInt(TemplateItinerary::getDay)
                .max()
                .orElse(1);  // 일정이 없으면 당일 여행으로 간주

        return startDate.plusDays(maxDay - 1);
    }
}