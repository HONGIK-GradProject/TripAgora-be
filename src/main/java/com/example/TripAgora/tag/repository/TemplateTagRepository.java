package com.example.TripAgora.tag.repository;

import com.example.TripAgora.tag.entity.TemplateTag;
import com.example.TripAgora.tag.entity.TemplateTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateTagRepository extends JpaRepository<TemplateTag, TemplateTagId> {
}