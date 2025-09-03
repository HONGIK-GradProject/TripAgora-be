package com.example.TripAgora.auth.service;

import com.example.TripAgora.auth.dto.ReissueRequest;
import com.example.TripAgora.auth.dto.ReissueResponse;
import com.example.TripAgora.auth.entity.RefreshToken;
import com.example.TripAgora.auth.exception.RefreshTokenInvalidException;
import com.example.TripAgora.auth.repository.RefreshTokenRepository;
import com.example.TripAgora.auth.util.JWTUtil;
import com.example.TripAgora.user.entity.User;
import com.example.TripAgora.user.exception.UserNotFoundException;
import com.example.TripAgora.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JWTUtil jwtUtil;

    public ReissueResponse reissue(ReissueRequest reissueRequest) {
        String refreshToken = reissueRequest.refreshToken();

        if(!existsRefreshToken(refreshToken)) {
            throw new RefreshTokenInvalidException();
        }

        if (!jwtUtil.isValid(refreshToken, false)) {
            throw new RefreshTokenInvalidException();
        }

        long userId = jwtUtil.getUserId(refreshToken);
        String role = jwtUtil.getRole(refreshToken);

        String newAccessToken = jwtUtil.createToken(userId, role, true);
        String newRefreshToken = jwtUtil.createToken(userId, role, false);

        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        deleteRefreshTokenByUserId(userId);
        saveRefreshToken(user, newRefreshToken);

        return new ReissueResponse(newAccessToken, newRefreshToken);
    }

    public void saveRefreshToken(User user, String token) {
        RefreshToken refreshEntity = RefreshToken.builder()
                .user(user)
                .token(token)
                .build();

        refreshTokenRepository.save(refreshEntity);
    }

    public Boolean existsRefreshToken(String token) {
        return refreshTokenRepository.existsByToken(token);
    }

    public void deleteRefreshTokenByUserId(Long userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }
}