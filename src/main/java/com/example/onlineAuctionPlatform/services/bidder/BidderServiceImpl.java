package com.example.onlineAuctionPlatform.services.bidder;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.onlineAuctionPlatform.entities.Bidder;

@Service
public class BidderServiceImpl implements BidderService {

    private BidderRepository bidderRepo;

    public BidderServiceImpl(BidderRepository bidderRepo) {
        this.bidderRepo = bidderRepo;
    }

    @Override
    public Bidder addByUsername(String username) {
        return bidderRepo.save(new Bidder(username));
    }

    @Override
    public Bidder save(Bidder bidder) {
        return bidderRepo.save(bidder);
    }

    @Override
    public Bidder getById(int bidderId) {
        Optional<Bidder> result = bidderRepo.findById(bidderId);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }
    
}
