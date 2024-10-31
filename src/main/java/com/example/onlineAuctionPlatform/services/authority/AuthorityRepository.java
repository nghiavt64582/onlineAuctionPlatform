package com.example.onlineAuctionPlatform.services.authority;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineAuctionPlatform.entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
        
}
