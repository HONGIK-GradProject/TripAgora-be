package com.example.TripAgora.session.repository;

import com.example.TripAgora.guideProfile.entity.GuideProfile;
import com.example.TripAgora.session.entity.Session;
import com.example.TripAgora.session.entity.SessionStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>, SessionRepositoryCustom {
    Slice<Session> findByStatusIn(List<SessionStatus> statuses, Pageable pageable);

    // 특정 가이드의 세션을 상태별로 페이징 조회 (statuses가 null이거나 비어있으면 이 조건은 무시됨)
    Slice<Session> findByTemplate_GuideProfileAndStatusIn(
            GuideProfile guideProfile, List<SessionStatus> statuses, Pageable pageable);

    // 특정 가이드의 특정 날짜 범위와 겹치는 세션이 있는지 확인 (특정 세션 제외)
    @Query("SELECT COUNT(s) > 0 FROM Session s " +
            "WHERE s.template.guideProfile = :guideProfile " +
            "AND s.id != :excludeSessionId " +
            "AND s.status IN ('RECRUITING', 'CONFIRMED') " +
            "AND s.startDate <= :endDate " +
            "AND s.endDate >= :startDate")
    boolean existsConflictingSession(
            @Param("guideProfile") GuideProfile guideProfile,
            @Param("excludeSessionId") Long excludeSessionId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    // 특정 가이드의 특정 날짜 범위와 겹치는 세션이 있는지 확인 (신규 생성용)
    @Query("SELECT COUNT(s) > 0 FROM Session s " +
            "WHERE s.template.guideProfile = :guideProfile " +
            "AND s.status IN ('RECRUITING', 'CONFIRMED') " +
            "AND s.startDate <= :endDate " +
            "AND s.endDate >= :startDate")
    boolean existsConflictingSession(
            @Param("guideProfile") GuideProfile guideProfile,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}