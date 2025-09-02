package com.example.TripAgora.user.repository;

import com.example.TripAgora.user.entity.SocialType;
import com.example.TripAgora.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySocialIdAndSocialType(String socialId, SocialType type);
}