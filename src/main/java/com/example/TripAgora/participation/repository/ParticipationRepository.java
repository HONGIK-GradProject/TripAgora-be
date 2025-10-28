package com.example.TripAgora.participation.repository;

import com.example.TripAgora.participation.entity.Participation;
import com.example.TripAgora.session.entity.Session;
import com.example.TripAgora.session.entity.SessionStatus;
import com.example.TripAgora.user.entity.Role;
import com.example.TripAgora.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    // TODO: 두 메서드 합치기?
    Optional<Participation> findByUserAndSession(User user, Session session);
    boolean existsByUser_IdAndSession_Id(long userId, long sessionId);

    Slice<Participation> findByUserAndRoleAndSession_StatusIn(
            User user,
            Role role,
            List<SessionStatus> statuses,
            Pageable pageable
    );

    Slice<Participation> findByUserAndRole(
            User user,
            Role role,
            Pageable pageable
    );
}