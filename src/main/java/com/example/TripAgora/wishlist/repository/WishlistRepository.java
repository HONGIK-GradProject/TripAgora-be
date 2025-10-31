package com.example.TripAgora.wishlist.repository;

import com.example.TripAgora.session.entity.Session;
import com.example.TripAgora.user.entity.User;
import com.example.TripAgora.wishlist.entity.Wishlist;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByUserAndSession(User user, Session session);
    boolean existsByUser_IdAndSession_Id(Long userId, Long sessionId);
    Slice<Wishlist> findByUser(User user, Pageable pageable);
}