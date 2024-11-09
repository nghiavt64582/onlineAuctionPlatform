package com.example.onlineAuctionPlatform.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineAuctionPlatform.entities.Bidder;

@RestController
public class TestController {

    @GetMapping("/info")
    public Bidder getInfo() {
        return new Bidder();
    }
}
