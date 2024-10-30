package com.example.onlineAuctionPlatform.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineAuctionPlatform.entities.BiddenPrice;
import com.example.onlineAuctionPlatform.entities.Bidder;
import com.example.onlineAuctionPlatform.services.bidden_price.BiddenPriceService;
import com.example.onlineAuctionPlatform.services.bidder.BidderService;

@RestController
@RequestMapping("/bidder")
public class BidderController {

    private BidderService bidderService;
    private BiddenPriceService biddenPriceService;
    
    public BidderController(
        BidderService bidderService,
        BiddenPriceService biddenPriceService
    ) {
        this.bidderService = bidderService;
        this.biddenPriceService = biddenPriceService;
    }

    @PutMapping("/bidder")
    public void updateInfo(@RequestBody Bidder bidder) {
        Bidder dbBidder = bidderService.getById(bidder.getId());
        if (dbBidder == null) {
            throw new RuntimeException("No valid bidder with id : " + bidder.getId());
        }
        dbBidder.setName(bidder.getName());
        dbBidder.setEmail(bidder.getEmail());
        dbBidder.updateCreatedDate();

        bidderService.save(dbBidder);
    }

    @PostMapping("/bidden_price")
    public void postBiddenPrice(@RequestBody BiddenPrice biddenPrice) {
        // BiddenPrice dbBiddenPrice;
        // biddenPriceService.save(dbBiddenPrice);
    }
}
