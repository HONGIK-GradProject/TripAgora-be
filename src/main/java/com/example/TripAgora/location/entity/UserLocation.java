package com.example.TripAgora.location.entity;

import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class UserLocation {

    @Id
    private Long userId; // PK: 사용자 ID (한 명당 한 줄만 존재)

    private Long chatRoomId; // 현재 참여 중인 채팅방

    private Double latitude;
    private Double longitude;

    private LocalDateTime lastUpdatedAt; // 마지막 갱신 시간

    // 생성자 및 업데이트 메서드
    public void updateLocation(Double lat, Double lng) {
        this.latitude = lat;
        this.longitude = lng;
        this.lastUpdatedAt = LocalDateTime.now();
    }
}