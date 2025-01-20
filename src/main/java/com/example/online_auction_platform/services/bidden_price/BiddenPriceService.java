package com.example.online_auction_platform.services.bidden_price;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.example.online_auction_platform.entities.BiddenPrice;

public interface BiddenPriceService {
    
    @Query("select u from bidden_price where u.productId = ?1")
    List<BiddenPrice> getBiddenPriceByProductId(int productId);
    
    @Query("select u from bidden_price where u.bidder_id = ?1")
    List<BiddenPrice> getBiddenPriceByBidderId(int bidderId);

    BiddenPrice add(BiddenPrice biddenPrice);

}