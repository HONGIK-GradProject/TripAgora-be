package com.example.TripAgora.guideProfile.service;

import com.example.TripAgora.auth.dto.ReissueResponse;
import com.example.TripAgora.auth.service.JWTService;
import com.example.TripAgora.guideProfile.entity.GuideProfile;
import com.example.TripAgora.guideProfile.exception.AlreadyGuideException;
import com.example.TripAgora.guideProfile.exception.AlreadyTravelerException;
import com.example.TripAgora.guideProfile.repository.GuideProfileRepository;
import com.example.TripAgora.user.dto.response.GuideSwitchResponse;
import com.example.TripAgora.user.dto.response.TravelerSwitchResponse;
import com.example.TripAgora.user.entity.User;
import com.example.TripAgora.user.exception.UserNotFoundException;
import com.example.TripAgora.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GuideProfileService {
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final GuideProfileRepository guideProfileRepository;

    @Transactional
    public GuideSwitchResponse switchToGuide(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if (user.getRole() == User.Role.GUIDE) {
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

        user.updateRole(User.Role.GUIDE);

        ReissueResponse tokenResponse = jwtService.issueTokensForUser(user);
        return new GuideSwitchResponse(tokenResponse.accessToken(), tokenResponse.refreshToken(), profile.getId());
        // TODO: 가이드용 홈화면 확정 후 수정
    }

    @Transactional
    public TravelerSwitchResponse switchToTraveler(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if (user.getRole() == User.Role.TRAVELER) {
            throw new AlreadyTravelerException();
        }

        user.updateRole(User.Role.TRAVELER);
        ReissueResponse tokenResponse = jwtService.issueTokensForUser(user);
        return new TravelerSwitchResponse(tokenResponse.accessToken(), tokenResponse.refreshToken(), user.getId());
        // TODO: 여행객용 홈화면 확정 후 수정
    }
}