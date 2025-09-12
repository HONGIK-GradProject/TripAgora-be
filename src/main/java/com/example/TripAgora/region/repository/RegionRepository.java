package com.example.TripAgora.region.repository;

import com.example.TripAgora.region.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}