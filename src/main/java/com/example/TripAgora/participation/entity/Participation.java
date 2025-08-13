package com.example.TripAgora.participation.entity;

import com.example.TripAgora.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "participation")
public class Participation extends BaseEntity {
    @Id
    @Column(name = "participation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
