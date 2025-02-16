package com.example.online_auction_platform.services;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.online_auction_platform.entities.Auctioneer;
import com.example.online_auction_platform.entities.Bidder;
import com.example.online_auction_platform.entities.Product;

import jakarta.annotation.PostConstruct;

@Service
public class CachingService {
    
    private ConcurrentHashMap<Integer, Product> productCache;
    private ConcurrentHashMap<Integer, Auctioneer> auctioneerCache;
    private ConcurrentHashMap<Integer, Bidder> bidderCache;

    @PostConstruct
    private void initMap() {
        productCache = new ConcurrentHashMap<>();
        auctioneerCache = new ConcurrentHashMap<>();
        bidderCache = new ConcurrentHashMap<>();
    }

    public void cacheProduct(int productId, Product product) {
        productCache.put(productId, product);
    }

    public void cacheAuctioneer(int auctioneerId, Auctioneer auctioneer) {
        auctioneerCache.put(auctioneerId, auctioneer);
    }

    public void cacheBidder(int bidderId, Bidder bidder) {
        bidderCache.put(bidderId, bidder);
    }

}
