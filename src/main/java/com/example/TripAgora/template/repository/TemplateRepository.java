package com.example.TripAgora.template.repository;

import com.example.TripAgora.guideProfile.entity.GuideProfile;
import com.example.TripAgora.template.entity.Template;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
    Slice<Template> findByGuideProfileOrderByIdDesc(GuideProfile guideProfile, Pageable pageable);
    List<Template> findByGuideProfile(GuideProfile guideProfile);
}