package com.example.TripAgora.region.repository;

import com.example.TripAgora.region.entity.TemplateRegion;
import com.example.TripAgora.region.entity.TemplateRegionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRegionRepository extends JpaRepository<TemplateRegion, TemplateRegionId> {
}