package com.example.TripAgora.guide.entity;

import com.example.TripAgora.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "guide")
public class Guide extends BaseEntity {
    @Id
    @Column(name = "guide_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}