package com.example.TripAgora.room.service;

import com.example.TripAgora.room.dto.request.NoticeCreateRequest;
import com.example.TripAgora.room.dto.response.NoticeResponse;
import com.example.TripAgora.room.entity.Notice;
import com.example.TripAgora.room.entity.Room;
import com.example.TripAgora.room.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final RoomService roomService;
    private final NoticeRepository noticeRepository;

    @Transactional
    public NoticeResponse createNotice(long userId, long roomId, NoticeCreateRequest request) {
        Room room = roomService.checkRoomAndGuide(userId, roomId);

        Notice notice = Notice.builder()
                .room(room)
                .title(request.title())
                .content(request.content())
                .build();
        Notice savedNotice = noticeRepository.save(notice);

        return new NoticeResponse(notice.getId(), notice.getTitle(), notice.getContent());
    }
}