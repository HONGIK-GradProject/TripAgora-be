package com.example.TripAgora.tag.repository;

import com.example.TripAgora.tag.entity.UserTag;
import com.example.TripAgora.tag.entity.UserTagId;
import com.example.TripAgora.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTagRepository extends JpaRepository<UserTag, UserTagId> {
    void deleteByUser(User user);
}