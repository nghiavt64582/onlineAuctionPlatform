package com.example.online_auction_platform.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_auction_platform.entities.BiddenPrice;
import com.example.online_auction_platform.entities.Bidder;
import com.example.online_auction_platform.requests.NameAndRangeRequest;
import com.example.online_auction_platform.services.bidden_price.BiddenPriceService;
import com.example.online_auction_platform.services.bidder.BidderService;

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
        Bidder dbBidder = bidderService.getById(bidder.getId());
        if (dbBidder == null) {
            throw new RuntimeException("No valid bidder with id : " + bidder.getId());
        }
        dbBidder.setName(bidder.getName());
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


    // normal sql
    @GetMapping("/bidder-test")
    public int countBidder(@RequestBody NameAndRangeRequest rq) {
        return bidderService.calcUserByNameInRange(rq.getMethod(), rq.getName(), rq.getMin(), rq.getMax());
    }
}
