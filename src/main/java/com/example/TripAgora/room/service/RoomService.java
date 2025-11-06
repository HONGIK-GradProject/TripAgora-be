package com.example.TripAgora.room.service;

import com.example.TripAgora.auth.exception.AccessDeniedException;
import com.example.TripAgora.participation.entity.Participation;
import com.example.TripAgora.participation.exception.ParticipationNotFoundException;
import com.example.TripAgora.participation.repository.ParticipationRepository;
import com.example.TripAgora.room.entity.Room;
import com.example.TripAgora.room.exception.RoomNotFoundException;
import com.example.TripAgora.room.repository.RoomRepository;
import com.example.TripAgora.user.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final ParticipationRepository participationRepository;

    protected Room checkRoomAndGuide(long userId, long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(RoomNotFoundException::new);
        Long sessionId = room.getSession().getId();
        Participation participation = participationRepository.findByUser_IdAndSession_Id(userId, sessionId).orElseThrow(ParticipationNotFoundException::new);

        if (participation.getRole() != Role.GUIDE) {
            throw new AccessDeniedException();
        }

        return room;
    }

    protected Room checkRoomAndParticipant(long userId, long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(RoomNotFoundException::new);
        Long sessionId = room.getSession().getId();
        participationRepository.findByUser_IdAndSession_Id(userId, sessionId).orElseThrow(ParticipationNotFoundException::new);

        return room;
    }
}