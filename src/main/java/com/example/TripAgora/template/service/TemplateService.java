package com.example.TripAgora.template.service;

import com.example.TripAgora.auth.exception.AccessDeniedException;
import com.example.TripAgora.guideProfile.entity.GuideProfile;
import com.example.TripAgora.guideProfile.exception.GuideProfileNotFoundException;
import com.example.TripAgora.region.entity.Region;
import com.example.TripAgora.region.entity.TemplateRegion;
import com.example.TripAgora.region.exception.InvalidRegionSelectionException;
import com.example.TripAgora.region.exception.RegionHierarchyConflictException;
import com.example.TripAgora.region.repository.RegionRepository;
import com.example.TripAgora.region.repository.TemplateRegionRepository;
import com.example.TripAgora.tag.entity.Tag;
import com.example.TripAgora.tag.entity.TemplateTag;
import com.example.TripAgora.tag.exception.InvalidTagSelectionException;
import com.example.TripAgora.tag.exception.MaximumTagsExceededException;
import com.example.TripAgora.tag.repository.TagRepository;
import com.example.TripAgora.tag.repository.TemplateTagRepository;
import com.example.TripAgora.template.dto.request.*;
import com.example.TripAgora.template.dto.response.*;
import com.example.TripAgora.template.entity.Template;
import com.example.TripAgora.template.entity.TemplateImage;
import com.example.TripAgora.template.exception.ItineraryDaySequenceInvalidException;
import com.example.TripAgora.template.exception.TemplateNotFoundException;
import com.example.TripAgora.template.repository.TemplateRepository;
import com.example.TripAgora.user.entity.User;
import com.example.TripAgora.user.exception.UserNotFoundException;
import com.example.TripAgora.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final RegionRepository regionRepository;
    private final TemplateTagRepository templateTagRepository;
    private final TemplateRegionRepository templateRegionRepository;

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

    @Transactional(readOnly = true)
    public TemplateDetailResponse getTemplate(long templateId) {
        Template template = templateRepository.findById(templateId).orElseThrow(TemplateNotFoundException::new);

        List<String> regions = template.getTemplateRegions().stream()
                .map(templateRegion -> templateRegion.getRegion().getName())
                .collect(Collectors.toList());

        List<String> tags = template.getTemplateTags().stream()
                .map(templateTag -> templateTag.getTag().getName())
                .collect(Collectors.toList());

        List<String> imageUrls = template.getTemplateImages().stream()
                .map(TemplateImage::getImageUrl)
                .toList();

        return new TemplateDetailResponse(
                template.getTitle(),
                template.getContent(),
                regions,
                tags,
                imageUrls);
    }

    @Transactional
    public void saveTemplate(long userId, long templateId, TemplateSaveRequest request) {
        Template template = findTemplateAndVerifyOwner(userId, templateId);

        template.updateTitle(request.title());
        template.updateContent(request.content());

        template.clearImages();
        if (request.imageUrls() != null) {
            request.imageUrls().forEach(template::addImage);
        }
    }

    @Transactional
    public TemplateTitleUpdateResponse updateTitle(long userId, long templateId, TemplateTitleUpdateRequest request) {
        Template template = findTemplateAndVerifyOwner(userId, templateId);
        template.updateTitle(request.title());

        return new TemplateTitleUpdateResponse(template.getTitle());
    }

    @Transactional
    public TemplateContentUpdateResponse updateContent(long userId, long templateId, TemplateContentUpdateRequest request) {
        Template template = findTemplateAndVerifyOwner(userId, templateId);
        template.updateContent(request.content());

        return new TemplateContentUpdateResponse(template.getContent());
    }

    @Transactional
    public void updateImages(long userId, long templateId, TemplateImageUpdateRequest request) {
        Template template = findTemplateAndVerifyOwner(userId, templateId);

        template.clearImages();
        if (request.imageUrls() != null) {
            request.imageUrls().forEach(template::addImage);
        }
    }

    @Transactional
    public TemplateTagUpdateResponse updateTags(long userId, long templateId, TemplateTagUpdateRequest request) {
        Template template = findTemplateAndVerifyOwner(userId, templateId);

        List<Long> tagIds = request.tagIds();

        if (tagIds.size() > 5) {
            throw new MaximumTagsExceededException();
        }

        List<Tag> newTags = tagRepository.findAllById(tagIds);
        if (newTags.size() != tagIds.size()) {
            throw new InvalidTagSelectionException();
        }

        templateTagRepository.deleteByTemplate(template);
        List<TemplateTag> newUserTags = newTags.stream()
                .map(tag -> TemplateTag.builder()
                        .template(template)
                        .tag(tag)
                        .build())
                .toList();
        templateTagRepository.saveAll(newUserTags);

        List<String> tagNames = newTags.stream()
                .map(Tag::getName)
                .collect(Collectors.toList());

        return new TemplateTagUpdateResponse(tagNames);
    }

    @Transactional
    public TemplateRegionUpdateResponse updateRegions(long userId, long templateId, TemplateRegionUpdateRequest request) {
        Template template = findTemplateAndVerifyOwner(userId, templateId);

        List<Long> regionIds = request.regionIds();

        List<Region> newRegions = regionRepository.findAllById(regionIds);
        if (newRegions.size() != regionIds.size()) {
            throw new InvalidRegionSelectionException();
        }

        for (Region region : newRegions) {
            if (region.getParent() != null && newRegions.contains(region.getParent())) {
                throw new RegionHierarchyConflictException();
            }
        }

        templateRegionRepository.deleteByTemplate(template);
        List<TemplateRegion> newTemplateRegions = newRegions.stream()
                .map(region -> TemplateRegion.builder()
                        .template(template)
                        .region(region)
                        .build())
                .toList();
        templateRegionRepository.saveAll(newTemplateRegions);

        List<String> regionNames = newRegions.stream()
                .map(Region::getName)
                .collect(Collectors.toList());

        return new TemplateRegionUpdateResponse(regionNames);
    }

    @Transactional
    public void updateItineraries(long userId, long templateId, TemplateItineraryUpdateRequest request) {
        Template template = findTemplateAndVerifyOwner(userId, templateId);

        Map<Integer, List<ItineraryItemRequest>> itinerariesByDay = request.itineraries().stream()
                .collect(Collectors.groupingBy(ItineraryItemRequest::day));

        int maxDay = itinerariesByDay.keySet().stream().max(Integer::compareTo).orElse(0);
        for (int i = 1; i <= maxDay; i++) {
            if (!itinerariesByDay.containsKey(i)) {
                throw new ItineraryDaySequenceInvalidException();
            }
        }

        template.clearItineraries();
        request.itineraries().forEach(template::addItinerary);
    }

    private Template findTemplateAndVerifyOwner(long userId, long templateId) {
        Template template = templateRepository.findById(templateId).orElseThrow(TemplateNotFoundException::new);

        if (!template.getGuideProfile().getUser().getId().equals(userId)) {
            throw new AccessDeniedException();
        }

        return template;
    }
}