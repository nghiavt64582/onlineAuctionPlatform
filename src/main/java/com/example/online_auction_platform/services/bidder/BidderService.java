package com.example.online_auction_platform.services.bidder;

import com.example.online_auction_platform.entities.Bidder;

public interface BidderService {

    Bidder save(Bidder bidder);

    Bidder findById(int id);
}
