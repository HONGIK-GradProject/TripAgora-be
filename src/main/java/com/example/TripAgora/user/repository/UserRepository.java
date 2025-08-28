package com.example.TripAgora.user.repository;

import com.example.TripAgora.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}