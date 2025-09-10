package com.example.TripAgora.guideProfile.repository;

import com.example.TripAgora.guideProfile.entity.GuideProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuideProfileRepository extends JpaRepository<GuideProfile, Long> {
    Optional<GuideProfile> findByUserId(Long userId);
}