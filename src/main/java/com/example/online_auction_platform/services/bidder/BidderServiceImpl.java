package com.example.online_auction_platform.services.bidder;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.online_auction_platform.entities.Bidder;

@Service
@Transactional() 
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
    public Bidder findById(int bidderId) {
        Optional<Bidder> result = bidderRepo.getBidderById(bidderId);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

}
