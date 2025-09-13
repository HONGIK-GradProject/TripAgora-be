package com.example.TripAgora.template.service;

import com.example.TripAgora.auth.exception.AccessDeniedException;
import com.example.TripAgora.guideProfile.entity.GuideProfile;
import com.example.TripAgora.guideProfile.exception.GuideProfileNotFoundException;
import com.example.TripAgora.template.dto.TemplateCreateResponse;
import com.example.TripAgora.template.dto.TemplateUpdateRequest;
import com.example.TripAgora.template.entity.Template;
import com.example.TripAgora.template.exception.TemplateNotFoundException;
import com.example.TripAgora.template.repository.TemplateRepository;
import com.example.TripAgora.user.entity.User;
import com.example.TripAgora.user.exception.UserNotFoundException;
import com.example.TripAgora.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;
    private final UserRepository userRepository;

    @Transactional
    public TemplateCreateResponse createDraftTemplate(long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        GuideProfile guideProfile = user.getGuideProfile();

        if (guideProfile == null) {
            throw new GuideProfileNotFoundException();
        }

        Template draftTemplate = Template.builder()
                .guideProfile(guideProfile)
                .build();

        Template savedTemplate = templateRepository.save(draftTemplate);
        return new TemplateCreateResponse(savedTemplate.getId());
    }

    @Transactional
    public void updateTemplate(long userId, long templateId, TemplateUpdateRequest request) {
        Template template = templateRepository.findById(templateId).orElseThrow(TemplateNotFoundException::new);

        if (!template.getGuideProfile().getUser().getId().equals(userId)) {
            throw new AccessDeniedException();
        }

        template.updateInfo(request.title(), request.content());

        template.clearImages();
        if (request.imageUrls() != null) {
            request.imageUrls().forEach(template::addImage);
        }
    }
}