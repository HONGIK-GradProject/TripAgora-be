package com.example.TripAgora.review.repository;

import com.example.TripAgora.guideProfile.entity.GuideProfile;
import com.example.TripAgora.review.entity.Review;
import com.example.TripAgora.session.entity.Session;
import com.example.TripAgora.template.entity.Template;
import com.example.TripAgora.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByAuthorAndSession(User author, Session session);
    List<Review> findByTemplate(Template template);
    Slice<Review> findByTemplate(Template template, Pageable pageable);
    List<Review> findByGuideProfile(GuideProfile guideProfile);
}