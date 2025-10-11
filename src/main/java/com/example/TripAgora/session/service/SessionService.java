package com.example.TripAgora.session.service;

import com.example.TripAgora.auth.exception.AccessDeniedException;
import com.example.TripAgora.guideProfile.entity.GuideProfile;
import com.example.TripAgora.guideProfile.exception.GuideProfileNotFoundException;
import com.example.TripAgora.session.dto.request.SessionCreateRequest;
import com.example.TripAgora.session.dto.request.SessionUpdateRequest;
import com.example.TripAgora.session.dto.response.SessionCreateResponse;
import com.example.TripAgora.session.dto.response.SessionDetailResponse;
import com.example.TripAgora.session.dto.response.SessionListResponse;
import com.example.TripAgora.session.dto.response.SessionSummaryResponse;
import com.example.TripAgora.session.entity.Session;
import com.example.TripAgora.session.entity.SessionItinerary;
import com.example.TripAgora.session.entity.SessionStatus;
import com.example.TripAgora.session.exception.InvalidMaxParticipantsException;
import com.example.TripAgora.session.exception.SessionNotFoundException;
import com.example.TripAgora.session.exception.SessionNotRecruitingException;
import com.example.TripAgora.session.repository.SessionRepository;
import com.example.TripAgora.template.entity.Template;
import com.example.TripAgora.template.service.TemplateService;
import com.example.TripAgora.user.entity.User;
import com.example.TripAgora.user.exception.UserNotFoundException;
import com.example.TripAgora.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final TemplateService templateService;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Transactional
    public SessionCreateResponse createSession(long userId, SessionCreateRequest request) {
        Template template = templateService.findTemplateAndVerifyOwner(userId, request.templateId());

        Session session = Session.builder()
                .template(template)
                .maxParticipants(request.maxParticipants())
                .startDate(request.startDate())
                .build();

        List<SessionItinerary> sessionItineraries = template.getTemplateItineraries().stream()
                .map(templateItinerary -> SessionItinerary.builder()
                        .session(session)
                        .templateItinerary(templateItinerary)
                        .build())
                .toList();

        sessionItineraries.forEach(session::addItinerary);

        Session savedSession = sessionRepository.save(session);

        return new SessionCreateResponse(savedSession.getId());
    }

    @Transactional(readOnly = true)
    public SessionDetailResponse getSessionDetails(long sessionId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(SessionNotFoundException::new);

        Template template = session.getTemplate();

        List<Long> regions = template.getTemplateRegions().stream()
                .map(templateRegion -> templateRegion.getRegion().getId())
                .collect(Collectors.toList());

        List<Long> tags = template.getTemplateTags().stream()
                .map(templateTag -> templateTag.getTag().getId())
                .collect(Collectors.toList());

        List<String> images = template.getTemplateImages().stream()
                .map(image -> image.getImageUrl())
                .collect(Collectors.toList());

        return new SessionDetailResponse(
                template.getTitle(),
                template.getContent(),
                regions,
                tags,
                images,
                session.getMaxParticipants(),
                session.getCurrentParticipants(),
                session.getStartDate(),
                session.getEndDate(),
                session.getStatus().name()
        );
    }

    @Transactional
    public void updateSession(long userId, long sessionId, SessionUpdateRequest request) {
        Session session = findSessionAndVerifyOwner(userId, sessionId);

        if (session.getStatus() != SessionStatus.RECRUITING) {
            throw new SessionNotRecruitingException();
        }

        if (request.maxParticipants() < session.getCurrentParticipants()) {
            throw new InvalidMaxParticipantsException();
        }

        session.updateInfo(request.maxParticipants(), request.startDate());
    }

    @Transactional
    public void deleteSession(long userId, long sessionId) {
        Session session = findSessionAndVerifyOwner(userId, sessionId);
        sessionRepository.delete(session);
    }

    @Transactional(readOnly = true)
    public SessionListResponse getMySessions(long userId, List<SessionStatus> statuses, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        GuideProfile guideProfile = user.getGuideProfile();
        if (guideProfile == null) {
            throw new GuideProfileNotFoundException();
        }

        return getSessionsByGuideProfile(guideProfile, statuses, pageable);
    }

    private Session findSessionAndVerifyOwner(long userId, long sessionId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(SessionNotFoundException::new);

        if (!session.getTemplate().getGuideProfile().getUser().getId().equals(userId)) {
            throw new AccessDeniedException();
        }

        return session;
    }

    private SessionListResponse getSessionsByGuideProfile(GuideProfile guideProfile, List<SessionStatus> statuses, Pageable pageable) {
        Slice<Session> sessionSlice = sessionRepository.findByTemplate_GuideProfileAndStatusIn(guideProfile, statuses, pageable);

        List<SessionSummaryResponse> summaries = sessionSlice.getContent().stream()
                .map(session -> {
                    Template template = session.getTemplate();
                    String imageUrl = template.getTemplateImages().isEmpty() ? null : template.getTemplateImages().get(0).getImageUrl();

                    List<String> regions = template.getTemplateRegions().stream()
                            .map(tr -> tr.getRegion().getName())
                            .toList();

                    return new SessionSummaryResponse(
                            session.getId(),
                            template.getTitle(),
                            imageUrl,
                            regions,
                            session.getMaxParticipants(),
                            session.getCurrentParticipants(),
                            session.getStartDate(),
                            session.getEndDate(),
                            session.getStatus().name()
                    );
                })
                .toList();

        return new SessionListResponse(summaries, sessionSlice.hasNext());
    }
}