package com.example.onlineAuctionPlatform.services.bidden_price;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.onlineAuctionPlatform.entities.BiddenPrice;

@Service
public class BiddenPriceServiceImpl implements BiddenPriceService {

    private BiddenPriceRepository biddenPriceRepo;

    public BiddenPriceServiceImpl(BiddenPriceRepository biddenPriceRepo) {
        this.biddenPriceRepo = biddenPriceRepo;
    }

    @Override
    public List<BiddenPrice> getBiddenPriceByProductId(int productId) {
        return new ArrayList<>();
    }

    @Override
    public List<BiddenPrice> getBiddenPriceByBidderId(int bidderId) {
        return new ArrayList<>();
    }
    
}
