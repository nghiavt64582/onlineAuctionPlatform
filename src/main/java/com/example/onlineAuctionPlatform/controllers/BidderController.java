package com.example.onlineAuctionPlatform.controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineAuctionPlatform.entities.Bidder;
import com.example.onlineAuctionPlatform.services.bidder.BidderService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/bidder")
public class BidderController {

    private static final Gson gson = new Gson();
    private BidderService bidderService;
    
    public BidderController(
        BidderService bidderService
    ) {
        this.bidderService = bidderService;
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
}
