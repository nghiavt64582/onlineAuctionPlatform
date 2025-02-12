package com.example.online_auction_platform.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_auction_platform.entities.BiddenPrice;
import com.example.online_auction_platform.entities.Bidder;
import com.example.online_auction_platform.services.BiddenPriceService;
import com.example.online_auction_platform.services.BidderService;

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
    public Bidder updateInfo(@RequestBody Bidder bidder) {
        Bidder dbBidder = bidderService.findById(bidder.getId());
        if (dbBidder == null) {
            throw new RuntimeException("No valid bidder with id : " + bidder.getId());
        }
        dbBidder.setUsername(bidder.getUsername());
        dbBidder.setEmail(bidder.getEmail());
        dbBidder.updateCreatedDate();

        bidderService.save(dbBidder);
        return dbBidder;
    }

    @PostMapping("/bidden-price")
    public BiddenPrice add(@RequestBody BiddenPrice biddenPrice) {
        BiddenPrice dbBiddenPrice = biddenPriceService.add(biddenPrice);
        dbBiddenPrice.updateCreatedDate();
        return dbBiddenPrice;
    }

}
