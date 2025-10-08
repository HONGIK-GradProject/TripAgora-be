package com.example.TripAgora.session.service;

import com.example.TripAgora.session.dto.request.SessionCreateRequest;
import com.example.TripAgora.session.dto.response.SessionCreateResponse;
import com.example.TripAgora.session.entity.Session;
import com.example.TripAgora.session.entity.SessionItinerary;
import com.example.TripAgora.session.repository.SessionRepository;
import com.example.TripAgora.template.entity.Template;
import com.example.TripAgora.template.entity.TemplateItinerary;
import com.example.TripAgora.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final TemplateService templateService;
    private final SessionRepository sessionRepository;

    @Transactional
    public SessionCreateResponse createSession(long userId, SessionCreateRequest request) {
        Template template = templateService.findTemplateAndVerifyOwner(userId, request.templateId());

        int maxDay = template.getTemplateItineraries().stream()
                .mapToInt(TemplateItinerary::getDay)
                .max()
                .orElse(0); // 일정이 없는 경우 0일
        LocalDate endDate = request.startDate().plusDays(maxDay > 0 ? maxDay - 1 : 0);

        Session session = Session.builder()
                .template(template)
                .maxParticipants(request.maxParticipants())
                .startDate(request.startDate())
                .endDate(endDate)
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
}