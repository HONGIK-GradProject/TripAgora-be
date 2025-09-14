package com.example.TripAgora.guideProfile.repository;

import com.example.TripAgora.guideProfile.entity.GuidePortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuidePortfolioRepository extends JpaRepository<GuidePortfolio, Long> {
}