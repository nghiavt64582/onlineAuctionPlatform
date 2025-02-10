package com.example.online_auction_platform.services.auctioneer;

import java.util.List;

import com.example.online_auction_platform.entities.Auctioneer;

public interface AuctioneerService {
    
    List<Auctioneer> getAllAutioneers();

    Auctioneer findById(int auctioneerId);

    boolean deleteById(int auctioneerId);

    Auctioneer save(Auctioneer auctioneer);
}
