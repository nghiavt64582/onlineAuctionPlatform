package com.example.onlineAuctionPlatform.services.auctioneer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineAuctionPlatform.entities.Auctioneer;

public interface AuctioneerRepository extends JpaRepository<Auctioneer, Integer> {
    
}
