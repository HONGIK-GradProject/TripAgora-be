package com.example.TripAgora.template.dto.response;

import java.time.LocalTime;
import java.util.List;

public record TemplateItinerariesResponse(List<ItineraryItemResponse> itineraries) {}