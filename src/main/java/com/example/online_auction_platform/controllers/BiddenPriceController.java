package com.example.online_auction_platform.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_auction_platform.dto.request.bidden_price.BiddingReqDto;
import com.example.online_auction_platform.dto.request.bidden_price.SellTheProductReqDto;
import com.example.online_auction_platform.services.BiddenPriceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/bidden-price")
public class BiddenPriceController {

    BiddenPriceService biddenPriceService;

    /*
     * Auctioneer accept the price and sell the product
     */
    @PostMapping("/sell")
    public String postMethodName(
        @RequestBody SellTheProductReqDto req
    ) {
        boolean success = biddenPriceService.processSellProduct(
            req.getAuctioneerId(),
            req.getProductId()
        );
        return String.valueOf(success);
    }

    @PostMapping("/bidding")
    public String postNewPrice(@RequestBody BiddingReqDto req) {
        boolean success = biddenPriceService.postNewPrice(
            req.getBidderId(),
            req.getProductId(),
            req.getPrice()
        );
        return "";
    }
    
    
    
    
}
