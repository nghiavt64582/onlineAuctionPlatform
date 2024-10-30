package com.example.onlineAuctionPlatform.services.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineAuctionPlatform.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
    
}
