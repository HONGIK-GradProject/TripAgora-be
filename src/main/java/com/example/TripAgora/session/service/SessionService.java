package com.example.TripAgora.session.service;

import com.example.TripAgora.auth.exception.AccessDeniedException;
import com.example.TripAgora.guideProfile.entity.GuideProfile;
import com.example.TripAgora.guideProfile.exception.GuideProfileNotFoundException;
import com.example.TripAgora.participation.entity.Participation;
import com.example.TripAgora.participation.repository.ParticipationRepository;
import com.example.TripAgora.room.entity.Room;
import com.example.TripAgora.room.repository.RoomRepository;
import com.example.TripAgora.session.dto.request.SessionCreateRequest;
import com.example.TripAgora.session.dto.request.SessionSearchRequest;
import com.example.TripAgora.session.dto.request.SessionUpdateRequest;
import com.example.TripAgora.session.dto.response.*;
import com.example.TripAgora.session.entity.Session;
import com.example.TripAgora.session.entity.SessionItinerary;
import com.example.TripAgora.session.entity.SessionStatus;
import com.example.TripAgora.session.exception.InvalidMaxParticipantsException;
import com.example.TripAgora.session.exception.SessionDateConflictException;
import com.example.TripAgora.session.exception.SessionNotFoundException;
import com.example.TripAgora.session.exception.SessionNotRecruitingException;
import com.example.TripAgora.session.repository.SessionRepository;
import com.example.TripAgora.template.entity.Template;
import com.example.TripAgora.template.service.TemplateService;
import com.example.TripAgora.user.entity.Role;
import com.example.TripAgora.user.entity.User;
import com.example.TripAgora.user.exception.UserNotFoundException;
import com.example.TripAgora.user.repository.UserRepository;
import com.example.TripAgora.wishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final TemplateService templateService;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final ParticipationRepository participationRepository;
    private final RoomRepository roomRepository;
    private final WishlistRepository wishlistRepository;

    @Transactional
    public SessionCreateResponse createSession(long userId, SessionCreateRequest request) {
        User guide = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Template template = templateService.findTemplateAndVerifyOwner(userId, request.templateId());

        LocalDate endDate = Session.calculateEndDate(template, request.startDate());

        boolean hasConflict = sessionRepository.existsConflictingSession(
                template.getGuideProfile(),
                request.startDate(),
                endDate
        );

        if (hasConflict) {
            throw new SessionDateConflictException();
        }

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

        Participation guideParticipation = Participation.builder()
                .user(guide)
                .session(session)
                .role(Role.GUIDE)
                .build();

        session.addParticipation(guideParticipation);

        return new SessionCreateResponse(savedSession.getId());
    }

    @Transactional(readOnly = true)
    public SessionDetailResponse getSessionDetails(long userId, long sessionId) {
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

        List<SessionParticipantResponse> participants = session.getParticipants().stream()
                .map(participation -> {
                    User user = participation.getUser();

                    return new SessionParticipantResponse(
                            user.getId(),
                            user.getNickname(),
                            user.getImageUrl(),
                            participation.getRole().name()
                    );
                })
                .toList();

        boolean isParticipating = participationRepository.existsByUser_IdAndSession_Id(userId, sessionId);
        boolean isMySession = Objects.equals(session.getTemplate().getGuideProfile().getUser().getId(), userId);
        boolean isInWishlist = wishlistRepository.existsByUser_IdAndSession_Id(userId, sessionId);

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
                session.getStatus().name(),
                participants,
                isParticipating,
                isMySession,
                isInWishlist
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

        if (!request.startDate().equals(session.getStartDate())) {
            LocalDate newEndDate = Session.calculateEndDate(session.getTemplate(), request.startDate());

            boolean hasConflict = sessionRepository.existsConflictingSession(
                    session.getTemplate().getGuideProfile(),
                    sessionId,
                    request.startDate(),
                    newEndDate
            );

            if (hasConflict) {
                throw new SessionDateConflictException();
            }
        }

        session.updateInfo(request.maxParticipants(), request.startDate());
    }

    @Transactional
    public void deleteSession(long userId, long sessionId) {
        Session session = findSessionAndVerifyOwner(userId, sessionId);
        sessionRepository.delete(session);
    }

    @Transactional(readOnly = true)
    public SessionListResponse getSessions(long userId, List<SessionStatus> statuses, Pageable pageable) {
        Slice<Session> sessionSlice;

        if (statuses == null || statuses.isEmpty()) {
            sessionSlice = sessionRepository.findAll(pageable);
        } else {
            sessionSlice = sessionRepository.findByStatusIn(statuses, pageable);
        }

        return convertToSessionListResponse(sessionSlice);
    }

    @Transactional(readOnly = true)
    public SessionListResponse getMySessions(long userId, List<SessionStatus> statuses, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        GuideProfile guideProfile = user.getGuideProfile();
        if (guideProfile == null) {
            throw new GuideProfileNotFoundException();
        }

        Slice<Session> sessionSlice = sessionRepository.findByTemplate_GuideProfileAndStatusIn(guideProfile, statuses, pageable);

        return convertToSessionListResponse(sessionSlice);
    }

    @Transactional(readOnly = true)
    public SessionListResponse getParticipatingSessions(long userId, List<SessionStatus> statuses, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        Slice<Participation> participationSlice;
        if (statuses == null || statuses.isEmpty()) {
            participationSlice = participationRepository.findByUserAndRole(user, Role.TRAVELER, pageable);
        } else {
            participationSlice = participationRepository.findByUserAndRoleAndSession_StatusIn(user, Role.TRAVELER, statuses, pageable);
        }

        Slice<Session> sessionSlice = participationSlice.map(Participation::getSession);

        return convertToSessionListResponse(sessionSlice);
    }

    @Transactional(readOnly = true)
    public SessionItinerariesResponse getItineraries(long sessionId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(SessionNotFoundException::new);

        List<SessionItineraryItemResponse> itineraries = session.getSessionItineraries().stream()
                .map(itinerary -> new SessionItineraryItemResponse(
                        itinerary.getId(),
                        itinerary.getDay(),
                        itinerary.getTitle(),
                        itinerary.getContent(),
                        itinerary.getStartTime(),
                        itinerary.getLatitude(),
                        itinerary.getLongitude()))
                .toList();

        return new SessionItinerariesResponse(itineraries);
    }

    @Transactional
    public void closeRecruitment(long userId, long sessionId) {
        Session session = findSessionAndVerifyOwner(userId, sessionId);

        if (session.getStatus() != SessionStatus.RECRUITING) {
            throw new SessionNotRecruitingException();
        }

        Room room = Room.builder()
                .session(session)
                .build();
        Room savedRoom = roomRepository.save(room);
        session.assignRoom(room);

        session.updateStatus(SessionStatus.RECRUITMENT_CLOSED);
    }

    @Transactional(readOnly = true)
    public SessionListResponse searchSessions(SessionSearchRequest request, Pageable pageable) {
        Slice<Session> sessionSlice = sessionRepository.search(request, pageable);

        return convertToSessionListResponse(sessionSlice);
    }

    private Session findSessionAndVerifyOwner(long userId, long sessionId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(SessionNotFoundException::new);

        if (!session.getTemplate().getGuideProfile().getUser().getId().equals(userId)) {
            throw new AccessDeniedException();
        }

        return session;
    }

    private SessionListResponse convertToSessionListResponse(Slice<Session> sessionSlice) {
        List<SessionSummaryResponse> summaries = sessionSlice.getContent().stream()
                .map(session -> {
                    Template template = session.getTemplate();
                    String imageUrl = template.getTemplateImages().isEmpty() ? null : template.getTemplateImages().get(0).getImageUrl();

                    List<Long> regions = template.getTemplateRegions().stream()
                            .map(tr -> tr.getRegion().getId())
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