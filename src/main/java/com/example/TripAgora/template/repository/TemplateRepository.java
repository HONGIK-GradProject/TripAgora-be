package com.example.TripAgora.template.repository;

import com.example.TripAgora.template.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}