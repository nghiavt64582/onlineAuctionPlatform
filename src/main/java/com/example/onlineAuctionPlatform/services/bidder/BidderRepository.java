package com.example.onlineAuctionPlatform.services.bidder;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineAuctionPlatform.entities.Bidder;

public interface BidderRepository extends JpaRepository<Bidder, Integer> {
    
}
