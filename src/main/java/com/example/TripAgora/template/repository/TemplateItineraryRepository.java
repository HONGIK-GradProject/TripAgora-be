package com.example.TripAgora.template.repository;

import com.example.TripAgora.template.entity.TemplateItinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateItineraryRepository extends JpaRepository<TemplateItinerary, Long> {
}