package com.example.online_auction_platform.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.online_auction_platform.entities.Auctioneer;
import com.example.online_auction_platform.repositories.AuctioneerRepository;

@Service
public class AuctioneerService {
    
    private AuctioneerRepository auctioneerRepo;

    public AuctioneerService(AuctioneerRepository auctioneerRepo) {
        this.auctioneerRepo = auctioneerRepo;
    }

    public List<Auctioneer> getAllAutioneers() {
        return auctioneerRepo.findAllAuctioneers();
    }

    public Auctioneer findById(int auctioneerId) {
        Optional<Auctioneer> result = auctioneerRepo.findById(auctioneerId);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("No auctineer with id " + auctioneerId);
        }
    }

    public boolean deleteById(int auctioneerId) {
        if  (auctioneerRepo.findById(auctioneerId).isEmpty()) {
            return false;
        }
        auctioneerRepo.deleteById(auctioneerId);
        return true;
    }

    public Auctioneer save(Auctioneer auctioneer) {
        return auctioneerRepo.save(auctioneer);
    }

}
