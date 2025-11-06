package com.example.TripAgora.template.dto.request;

import jakarta.validation.Valid;

import java.util.List;

public record ItineraryUpdateRequest(
        @Valid
        List<ItineraryItemRequest> itineraries) {}