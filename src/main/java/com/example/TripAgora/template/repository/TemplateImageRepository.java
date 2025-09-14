package com.example.TripAgora.template.repository;

import com.example.TripAgora.template.entity.TemplateImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateImageRepository extends JpaRepository<TemplateImage, Long> {
}