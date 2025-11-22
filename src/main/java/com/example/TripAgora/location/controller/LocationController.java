package com.example.TripAgora.location.controller;

import com.example.TripAgora.common.code.SuccessCode;
import com.example.TripAgora.common.response.ApiResponse;
import com.example.TripAgora.location.dto.response.LocationResponse;
import com.example.TripAgora.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @GetMapping("rooms/{roomId}/locations")
    public ApiResponse<List<LocationResponse>> getLocations(@AuthenticationPrincipal final long userId,
                                                            @PathVariable final long roomId) {
        List<LocationResponse> response = locationService.getRoomLocations(userId, roomId);
        return ApiResponse.success(SuccessCode.OK, response);
    }
}