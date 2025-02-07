package com.example.online_auction_platform.services.bidden_price;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online_auction_platform.entities.BiddenPrice;

public interface BiddenPriceRepository extends JpaRepository<BiddenPrice, Integer> {

}
