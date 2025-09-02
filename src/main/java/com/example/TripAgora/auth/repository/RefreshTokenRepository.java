package com.example.TripAgora.auth.repository;

import com.example.TripAgora.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshTokenRepository extends JpaRepository <RefreshToken, Long> {
    Boolean existsByToken(String refreshToken);

    @Transactional
    void deleteByToken(String refreshToken);

    @Transactional
    void deleteByUserId(Long userId);
}