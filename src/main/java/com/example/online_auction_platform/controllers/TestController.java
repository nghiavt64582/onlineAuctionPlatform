package com.example.online_auction_platform.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_auction_platform.entities.Bidder;

@RestController
public class TestController {

    @GetMapping("/info")
    public Bidder getInfo() {
        return new Bidder();
    }
}
