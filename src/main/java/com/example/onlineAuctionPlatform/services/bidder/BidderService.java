package com.example.onlineAuctionPlatform.services.bidder;

import com.example.onlineAuctionPlatform.entities.Bidder;

public interface BidderService {
     
    Bidder addByUsername(String username);

    Bidder save(Bidder bidder);

    Bidder getById(int id);
}
