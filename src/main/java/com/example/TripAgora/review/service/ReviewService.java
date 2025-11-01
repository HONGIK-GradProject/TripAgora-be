package com.example.TripAgora.review.service;

import com.example.TripAgora.auth.exception.AccessDeniedException;
import com.example.TripAgora.guideProfile.entity.GuideProfile;
import com.example.TripAgora.participation.repository.ParticipationRepository;
import com.example.TripAgora.review.dto.request.ReviewCreateRequest;
import com.example.TripAgora.review.dto.request.ReviewUpdateRequest;
import com.example.TripAgora.review.dto.response.ReviewResponse;
import com.example.TripAgora.review.entity.Review;
import com.example.TripAgora.review.exception.AlreadyReviewedException;
import com.example.TripAgora.review.exception.ReviewNotAllowedException;
import com.example.TripAgora.review.exception.ReviewNotFoundException;
import com.example.TripAgora.review.exception.SessionNotCompletedException;
import com.example.TripAgora.review.repository.ReviewRepository;
import com.example.TripAgora.session.entity.Session;
import com.example.TripAgora.session.entity.SessionStatus;
import com.example.TripAgora.session.exception.SessionNotFoundException;
import com.example.TripAgora.session.repository.SessionRepository;
import com.example.TripAgora.template.entity.Template;
import com.example.TripAgora.template.repository.TemplateRepository;
import com.example.TripAgora.user.entity.Role;
import com.example.TripAgora.user.entity.User;
import com.example.TripAgora.user.exception.UserNotFoundException;
import com.example.TripAgora.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final TemplateRepository templateRepository;
    private final ParticipationRepository participationRepository;

    public ReviewResponse createReview(Long userId, ReviewCreateRequest request) {
        User author = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Session session = sessionRepository.findById(request.sessionId()).orElseThrow(SessionNotFoundException::new);
        Template template = session.getTemplate();
        GuideProfile guideProfile = template.getGuideProfile();

        validateReviewer(author, session);

         if (session.getStatus() != SessionStatus.COMPLETED) {
             throw new SessionNotCompletedException();
         }

        if (reviewRepository.existsByAuthorAndSession(author, session)) {
            throw new AlreadyReviewedException();
        }

        Review review = Review.builder()
                .author(author)
                .session(session)
                .template(template)
                .guideProfile(guideProfile)
                .content(request.content())
                .rating(request.rating())
                .build();
        Review savedReview = reviewRepository.save(review);

        updateTemplateRating(template);
        updateGuideProfileRating(guideProfile);

        return new ReviewResponse(
                savedReview.getId(),
                savedReview.getAuthor().getNickname(),
                savedReview.getAuthor().getImageUrl(),
                savedReview.getContent(),
                savedReview.getRating(),
                savedReview.getCreatedAt()
                );
    }

    public ReviewResponse updateReview(Long userId, Long reviewId, ReviewUpdateRequest request) {
        Review review = findReviewAndVerifyOwner(userId, reviewId);

        review.update(request.content(), request.rating());

        updateTemplateRating(review.getTemplate());
        updateGuideProfileRating(review.getGuideProfile());

        return new ReviewResponse(
                review.getId(),
                review.getAuthor().getNickname(),
                review.getAuthor().getImageUrl(),
                review.getContent(),
                review.getRating(),
                review.getCreatedAt()
        );
    }



    private void updateTemplateRating(Template template) {
        List<Review> reviews = reviewRepository.findByTemplate(template);

        int reviewCount = reviews.size();
        double avgRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        template.updateRating(avgRating, reviewCount);
    }

    private void updateGuideProfileRating(GuideProfile guideProfile) {
        List<Template> templates = templateRepository.findByGuideProfile(guideProfile);

        int totalReviewCount = templates.stream()
                .mapToInt(Template::getReviewCount)
                .sum();

        double avgOfTemplateAvgs = templates.stream()
                .filter(t -> t.getReviewCount() > 0)
                .mapToDouble(Template::getAvgRating)
                .average()
                .orElse(0.0);

        guideProfile.updateRating(avgOfTemplateAvgs, totalReviewCount);
    }

    private void validateReviewer(User user, Session session) {
        participationRepository.findByUserAndSession(user, session)
                .filter(p -> p.getRole() == Role.TRAVELER)
                .orElseThrow(ReviewNotAllowedException::new);
    }

    private Review findReviewAndVerifyOwner(Long userId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);

        if (!Objects.equals(review.getAuthor().getId(), userId)) {
            throw new AccessDeniedException();
        }

        return review;
    }
}