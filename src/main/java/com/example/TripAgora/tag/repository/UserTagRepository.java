package com.example.TripAgora.tag.repository;

import com.example.TripAgora.tag.entity.UserTag;
import com.example.TripAgora.tag.entity.UserTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTagRepository extends JpaRepository<UserTag, UserTagId> {

}