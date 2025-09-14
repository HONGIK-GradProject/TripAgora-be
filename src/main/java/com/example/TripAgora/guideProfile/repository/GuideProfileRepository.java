package com.example.TripAgora.guideProfile.repository;

import com.example.TripAgora.guideProfile.entity.GuideProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideProfileRepository extends JpaRepository<GuideProfile, Long> {
}