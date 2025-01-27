package com.example.online_auction_platform.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_auction_platform.services.bidden_price.BiddenPriceService;

@RestController
@RequestMapping("/bidden-price")
public class BiddenPriceController {

    BiddenPriceService biddenPriceService;
    
    
}
