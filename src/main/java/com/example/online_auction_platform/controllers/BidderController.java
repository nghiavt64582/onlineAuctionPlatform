package com.example.online_auction_platform.controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_auction_platform.entities.Bidder;
import com.example.online_auction_platform.services.BidderService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/bidder")
@AllArgsConstructor
public class BidderController {

    private BidderService bidderService;

    @PutMapping("/")
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

}
