package com.example.TripAgora.session.repository;

import com.example.TripAgora.guideProfile.entity.GuideProfile;
import com.example.TripAgora.session.entity.Session;
import com.example.TripAgora.session.entity.SessionStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    // 특정 가이드의 세션을 상태별로 페이징 조회 (statuses가 null이거나 비어있으면 이 조건은 무시됨)
    Slice<Session> findByTemplate_GuideProfileAndStatusIn(GuideProfile guideProfile, List<SessionStatus> statuses, Pageable pageable);
}