package com.example.TripAgora.auth.service;

import com.example.TripAgora.auth.entity.RefreshToken;
import com.example.TripAgora.auth.repository.RefreshTokenRepository;
import com.example.TripAgora.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JWTService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void saveRefreshToken(User user, String token) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(token)
                .build();

        refreshTokenRepository.save(refreshToken);
    }

    @Transactional(readOnly = true)
    public Boolean existsRefreshToken(String token) {
        return refreshTokenRepository.existsByToken(token);
    }

    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }

    public void deleteRefreshTokenByUserId(Long userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }
}