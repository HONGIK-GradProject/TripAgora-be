package com.example.TripAgora.participation.repository;

import com.example.TripAgora.participation.entity.Participation;
import com.example.TripAgora.session.entity.Session;
import com.example.TripAgora.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    Optional<Participation> findByUserAndSession(User user, Session session);
}