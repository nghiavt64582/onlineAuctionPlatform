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
    public Bidder getById(int bidderId) {
        Optional<Bidder> result = bidderRepo.findById(bidderId);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    @Override
    public int calcUserByNameInRange(String method, String name, int min, int max) {
        long start = System.currentTimeMillis();
        int result = 0;
        for (int bidderId = min; bidderId <= max; bidderId++) {
            Optional<Bidder> opBidder = Optional.empty();
            switch (method) {
                case "PlanCache":
                    opBidder = bidderRepo.findById(bidderId);
                    break;
                case "NormalSQL":
                    opBidder = bidderRepo.findByBidderId(bidderId);
                    break;
                case "StoredProcedure":
                    opBidder = bidderRepo.findByBidderIdProcedure(bidderId);
                    break;
                default:
                    break;
            }
            if (opBidder.isPresent()) {
                Bidder bidder = opBidder.get();
                result += bidder.getName().contains(name) ? 1 : 0;
            }
        }
        System.out.println("Total time by using " + method + " : " + (System.currentTimeMillis() - start) + " ms to get " + (max - min) + " records by id.");
        return result;
    }
}
