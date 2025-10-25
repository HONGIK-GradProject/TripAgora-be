package com.example.TripAgora.room.repository;

import com.example.TripAgora.room.entity.Notice;
import com.example.TripAgora.room.entity.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Slice<Notice> findByRoomOrderByIdDesc(Room room, Pageable pageable);
}