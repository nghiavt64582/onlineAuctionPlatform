package com.example.online_auction_platform.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_auction_platform.services.AuctioneerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auctioneer")
@AllArgsConstructor
public class AuctioneerController {

    private AuctioneerService auctioneerService;
}
