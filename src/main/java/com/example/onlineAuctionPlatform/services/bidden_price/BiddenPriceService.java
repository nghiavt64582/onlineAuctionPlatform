package com.example.onlineAuctionPlatform.services.bidden_price;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.example.onlineAuctionPlatform.entities.BiddenPrice;

public interface BiddenPriceService {
    
    @Query("select * from bidden_price where product_id = %?1%")
    List<BiddenPrice> getBiddenPriceByProductId(int productId);
    
    @Query("select * from bidden_price where bidder_id = %?1%")
    List<BiddenPrice> getBiddenPriceByBidderId(int bidderId);

}