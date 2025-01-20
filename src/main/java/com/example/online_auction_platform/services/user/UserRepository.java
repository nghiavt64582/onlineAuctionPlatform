package com.example.online_auction_platform.services.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online_auction_platform.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
    
}
