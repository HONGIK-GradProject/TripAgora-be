package com.example.TripAgora.room.service;

import com.example.TripAgora.auth.exception.AccessDeniedException;
import com.example.TripAgora.room.dto.request.NoticeCreateRequest;
import com.example.TripAgora.room.dto.request.NoticeUpdateRequest;
import com.example.TripAgora.room.dto.response.NoticeResponse;
import com.example.TripAgora.room.entity.Notice;
import com.example.TripAgora.room.entity.Room;
import com.example.TripAgora.room.exception.NoticeNotFoundException;
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

    @Transactional(readOnly = true)
    public NoticeResponse getNotice(long userId, long roomId, long noticeId) {
        roomService.checkRoomAndParticipant(userId, roomId);
        Notice notice = checkNoticeAndRoom(noticeId, roomId);

        return new NoticeResponse(notice.getId(), notice.getTitle(), notice.getContent());
    }

    @Transactional
    public NoticeResponse updateNotice(long userId, long roomId, long noticeId, NoticeUpdateRequest request) {
        roomService.checkRoomAndGuide(userId, roomId);
        Notice notice = checkNoticeAndRoom(noticeId, roomId);

        notice.update(request.title(), request.content());

        return new NoticeResponse(notice.getId(), notice.getTitle(), notice.getContent());
    }

    @Transactional
    public void deleteNotice(long userId, long roomId, long noticeId) {
        roomService.checkRoomAndGuide(userId, roomId);
        Notice notice = checkNoticeAndRoom(noticeId, roomId);

        noticeRepository.delete(notice);
    }

    private Notice checkNoticeAndRoom(long noticeId, long roomId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(NoticeNotFoundException::new);

        if (!notice.getRoom().getId().equals(roomId)) {
            throw new AccessDeniedException();
        }

        return notice;
    }
}