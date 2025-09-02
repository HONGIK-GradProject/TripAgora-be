package com.example.TripAgora.auth.service;

import com.example.TripAgora.auth.dto.*;
import com.example.TripAgora.auth.exception.SocialAccessTokenInvalidException;
import com.example.TripAgora.auth.exception.SocialJsonParseErrorException;
import com.example.TripAgora.auth.repository.RefreshTokenRepository;
import com.example.TripAgora.auth.util.JWTUtil;
import com.example.TripAgora.user.entity.SocialType;
import com.example.TripAgora.user.entity.User;
import com.example.TripAgora.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final JWTService jwtService;
    private final JWTUtil jwtUtil;
    private final RestTemplate restTemplate = new RestTemplate();

    private final String KAKAO_USERINFO_URL = "https://kapi.kakao.com/v2/user/me";
    private final String NAVER_USERINFO_URL = "https://openapi.naver.com/v1/nid/me";
    private final RefreshTokenRepository refreshTokenRepository;

    public SocialLoginResponse socialLogin(SocialLoginRequest request, SocialType socialType) {
        String userInfoJson = fetchUserInfo(request.socialAccessToken(), socialType);
        SocialUserInfo userInfo = parseUserInfo(userInfoJson, socialType);

        AtomicBoolean isNewUser = new AtomicBoolean(false);
        User user = saveOrUpdateUser(userInfo.id(), userInfo.profileImageUrl(), socialType, isNewUser);

        String accessToken = jwtUtil.createToken(user.getId(), user.getRole().name(), true);
        String refreshToken = jwtUtil.createToken(user.getId(), user.getRole().name(), false);

        jwtService.deleteRefreshTokenByUserId(user.getId());
        jwtService.saveRefreshToken(user, refreshToken);

        return new SocialLoginResponse(accessToken, refreshToken, isNewUser.get());
    }

    private String fetchUserInfo(String socialAccessToken, SocialType socialType) {
        String url = switch (socialType) {
            case KAKAO -> KAKAO_USERINFO_URL;
            case NAVER -> NAVER_USERINFO_URL;
        };

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(socialAccessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response =
                    restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new SocialAccessTokenInvalidException();
        } catch (Exception e) {
            throw new RuntimeException("소셜 로그인 API 요청 실패", e);
        }
    }

    private SocialUserInfo parseUserInfo(String json, SocialType type) {
        try {
            return switch (type) {
                case KAKAO -> {
                    KakaoUserInfo kakao = objectMapper.readValue(json, KakaoUserInfo.class);
                    yield new SocialUserInfo(
                            kakao.getId().toString(),
                            kakao.getKakaoAccount().getProfile().getProfileImageUrl()
                    );
                }
                case NAVER -> {
                    NaverUserInfo naver = objectMapper.readValue(json, NaverUserInfo.class);
                    yield new SocialUserInfo(
                            naver.getResponse().getId(),
                            naver.getResponse().getProfile_image()
                    );
                }
            };
        } catch (JsonProcessingException e) {
            throw new SocialJsonParseErrorException();
        }
    }

    private User saveOrUpdateUser(String socialId, String profileImageUrl, SocialType type, AtomicBoolean isNewUser) {
        Optional<User> userOpt = userRepository.findBySocialIdAndSocialType(socialId, type);

        if (userOpt.isPresent()) {
            isNewUser.set(false);
            return userOpt.get();
        } else {
            User newUser = User.builder()
                    .socialId(socialId)
                    .socialType(type)
                    .imageUrl(profileImageUrl)
                    .build();
            isNewUser.set(true);

            return userRepository.save(newUser);
        }
    }

    @Transactional
    public void logout(Long userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }
}