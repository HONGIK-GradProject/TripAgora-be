package com.example.TripAgora.session.entity;

import com.example.TripAgora.common.entity.BaseEntity;
import com.example.TripAgora.participation.entity.Participation;
import com.example.TripAgora.room.entity.Room;
import com.example.TripAgora.session.exception.SessionFullException;
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

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participation> participants = new ArrayList<>();

    @OneToOne(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private Room room;

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

    public void addParticipation(Participation participation) {
        this.participants.add(participation);
    }

    public void assignRoom(Room room) {
        this.room = room;
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

    public void increaseParticipant() {
        if (this.currentParticipants >= this.maxParticipants) {
            throw new SessionFullException();
        }

        this.currentParticipants++;
    }

    public void decreaseParticipant() {
        if (this.currentParticipants > 0) {
            this.currentParticipants--;
        }
    }

    public static LocalDate calculateEndDate(Template template, LocalDate startDate) {
        int maxDay = template.getTemplateItineraries().stream()
                .mapToInt(TemplateItinerary::getDay)
                .max()
                .orElse(1);  // 일정이 없으면 당일 여행으로 간주

        return startDate.plusDays(maxDay - 1);
    }
}