package com.example.onlineAuctionPlatform.services.auctioneer;

import java.util.List;

import com.example.onlineAuctionPlatform.entities.Auctioneer;

public interface AuctioneerService {
    
    List<Auctioneer> getAllAutioneers();

    Auctioneer getById(int auctioneerId);

    boolean deleteById(int auctioneerId);

    Auctioneer save(Auctioneer auctioneer);

    Auctioneer addAuctioneerByUsername(String username);
}
