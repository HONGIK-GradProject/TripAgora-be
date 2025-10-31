package com.example.TripAgora.session.repository;

import com.example.TripAgora.session.dto.request.SessionSearchRequest;
import com.example.TripAgora.session.entity.Session;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface SessionRepositoryCustom {
    Slice<Session> search(SessionSearchRequest request, Pageable pageable);
}