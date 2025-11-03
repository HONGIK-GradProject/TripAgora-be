package com.example.TripAgora.guideProfile.service;

import com.example.TripAgora.auth.dto.ReissueResponse;
import com.example.TripAgora.auth.service.JWTService;
import com.example.TripAgora.guideProfile.dto.response.GuideProfileDetailResponse;
import com.example.TripAgora.guideProfile.dto.response.PortfolioResponse;
import com.example.TripAgora.guideProfile.entity.GuideProfile;
import com.example.TripAgora.guideProfile.exception.AlreadyGuideException;
import com.example.TripAgora.guideProfile.exception.AlreadyTravelerException;
import com.example.TripAgora.guideProfile.exception.GuideProfileNotFoundException;
import com.example.TripAgora.guideProfile.repository.GuideProfileRepository;
import com.example.TripAgora.session.dto.response.SessionListResponse;
import com.example.TripAgora.session.entity.Session;
import com.example.TripAgora.session.entity.SessionStatus;
import com.example.TripAgora.session.repository.SessionRepository;
import com.example.TripAgora.session.service.SessionService;
import com.example.TripAgora.user.dto.response.GuideSwitchResponse;
import com.example.TripAgora.user.dto.response.TravelerSwitchResponse;
import com.example.TripAgora.user.entity.Role;
import com.example.TripAgora.user.entity.User;
import com.example.TripAgora.user.exception.UserNotFoundException;
import com.example.TripAgora.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuideProfileService {
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final GuideProfileRepository guideProfileRepository;
    private final SessionRepository sessionRepository;

    @Transactional
    public GuideSwitchResponse switchToGuide(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if (user.getRole() == Role.GUIDE) {
            throw new AlreadyGuideException();
        }

        GuideProfile profile = null;
        if (user.getGuideProfile() == null) {
            GuideProfile newProfile = GuideProfile.builder()
                    .user(user)
                    .build();
            profile = guideProfileRepository.save(newProfile);
        } else {
            profile = user.getGuideProfile();
        }

        user.updateRole(Role.GUIDE);

        ReissueResponse tokenResponse = jwtService.issueTokensForUser(user);
        return new GuideSwitchResponse(tokenResponse.accessToken(), tokenResponse.refreshToken(), profile.getId());
        // TODO: 가이드용 홈화면 확정 후 수정
    }

    @Transactional
    public TravelerSwitchResponse switchToTraveler(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if (user.getRole() == Role.TRAVELER) {
            throw new AlreadyTravelerException();
        }

        user.updateRole(Role.TRAVELER);
        ReissueResponse tokenResponse = jwtService.issueTokensForUser(user);
        return new TravelerSwitchResponse(tokenResponse.accessToken(), tokenResponse.refreshToken(), user.getId());
        // TODO: 여행객용 홈화면 확정 후 수정
    }

    public GuideProfileDetailResponse getGuideProfileDetails(Long guideProfileId, Pageable pageable) {
        GuideProfile guideProfile = guideProfileRepository.findById(guideProfileId).orElseThrow(GuideProfileNotFoundException::new);
        User user = guideProfile.getUser();

        List<Long> tagIds = user.getUserTags().stream()
                .map(templateTag -> templateTag.getTag().getId())
                .toList();

        List<PortfolioResponse> portfolios = guideProfile.getPortfolios().stream()
                .map(portfolio -> new PortfolioResponse(
                        portfolio.getType().name(),
                        portfolio.getUrl()))
                .collect(Collectors.toList());

        List<SessionStatus> statuses = new ArrayList<>();
        statuses.add(SessionStatus.RECRUITING);
        Slice<Session> sessionSlice = sessionRepository.findByTemplate_GuideProfileAndStatusIn(guideProfile, statuses, pageable);

        SessionListResponse sessionListResponse = SessionService.getSessionListResponse(sessionSlice);

        return new GuideProfileDetailResponse(
                user.getNickname(),
                guideProfile.getImageUrl(),
                guideProfile.getBio(),
                guideProfile.getTotalAvgRating(),
                guideProfile.getTotalReviewCount(),
                tagIds,
                portfolios,
                sessionListResponse
        );
    }
}