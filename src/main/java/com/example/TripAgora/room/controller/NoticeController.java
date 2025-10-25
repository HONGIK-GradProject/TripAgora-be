package com.example.TripAgora.room.controller;

import com.example.TripAgora.common.code.SuccessCode;
import com.example.TripAgora.common.response.ApiResponse;
import com.example.TripAgora.room.dto.request.NoticeCreateRequest;
import com.example.TripAgora.room.dto.request.NoticeUpdateRequest;
import com.example.TripAgora.room.dto.response.NoticeResponse;
import com.example.TripAgora.room.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms/{roomId}/notices")
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping
    public ApiResponse<NoticeResponse> createNotice(@AuthenticationPrincipal final long userId,
                                                    @PathVariable final long roomId,
                                                    @RequestBody @Valid final NoticeCreateRequest request) {
        NoticeResponse response = noticeService.createNotice(userId, roomId, request);
        return ApiResponse.success(SuccessCode.CREATED, response);
    }

    @PatchMapping("/{noticeId}")
    public ApiResponse<NoticeResponse> updateNotice(@AuthenticationPrincipal final long userId,
                                                    @PathVariable final long roomId,
                                                    @PathVariable final long noticeId,
                                                    @RequestBody @Valid final NoticeUpdateRequest request) {
        NoticeResponse response = noticeService.updateNotice(userId, roomId, noticeId, request);
        return ApiResponse.success(SuccessCode.OK, response);
    }

    @DeleteMapping("/{noticeId}")
    public ApiResponse<Void> deleteNotice(@AuthenticationPrincipal final long userId,
                                          @PathVariable final long roomId,
                                          @PathVariable final long noticeId) {
        noticeService.deleteNotice(userId, roomId, noticeId);
        return ApiResponse.success(SuccessCode.OK);
    }
}