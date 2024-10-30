package com.example.onlineAuctionPlatform.services.bidden_price;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineAuctionPlatform.entities.BiddenPrice;

public interface BiddenPriceRepository extends JpaRepository<BiddenPrice, Integer> {
    
}
