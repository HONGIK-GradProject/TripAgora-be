package com.example.TripAgora.region.repository;

import com.example.TripAgora.region.entity.TemplateRegion;
import com.example.TripAgora.region.entity.TemplateRegionId;
import com.example.TripAgora.template.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRegionRepository extends JpaRepository<TemplateRegion, TemplateRegionId> {
    void deleteByTemplate(Template template);
}