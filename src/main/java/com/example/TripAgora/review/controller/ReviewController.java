package com.example.TripAgora.review.controller;

import com.example.TripAgora.common.code.SuccessCode;
import com.example.TripAgora.common.response.ApiResponse;
import com.example.TripAgora.review.dto.request.ReviewCreateRequest;
import com.example.TripAgora.review.dto.response.ReviewResponse;
import com.example.TripAgora.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping()
    public ApiResponse<ReviewResponse> createReview(@AuthenticationPrincipal final long userId,
                                                    @RequestBody @Valid final ReviewCreateRequest request) {
        ReviewResponse response = reviewService.createReview(userId, request);
        return ApiResponse.success(SuccessCode.CREATED, response);
    }
}
