package com.example.onlineAuctionPlatform.services.bidder;

import org.springframework.stereotype.Service;

import com.example.onlineAuctionPlatform.entities.Bidder;

@Service
public class BidderServiceImpl implements BidderService {

    private BidderRepository bidderRepo;

    public BidderServiceImpl(BidderRepository bidderRepo) {
        this.bidderRepo = bidderRepo;
    }

    @Override
    public Bidder addBidderByUsername(String username) {
        return bidderRepo.save(new Bidder(username));
    }
    
}
