package com.example.online_auction_platform.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.online_auction_platform.entities.Auctioneer;
import com.example.online_auction_platform.repositories.AuctioneerRepository;

@Service
public class AuctioneerService {
    
    private AuctioneerRepository auctioneerRepo;

    public AuctioneerService(AuctioneerRepository auctioneerRepo) {
        this.auctioneerRepo = auctioneerRepo;
    }

    public Page<Auctioneer> getAllAutioneers() {
        Pageable pageable = PageRequest.of(0, 100, Sort.by("ASC"));
        return auctioneerRepo.findAllAuctioneers(pageable);
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
