package com.example.online_auction_platform.services.auctioneer;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.online_auction_platform.entities.Auctioneer;

@Service
public class AuctioneerServiceImpl implements AuctioneerService {
    
    private AuctioneerRepository auctioneerRepo;

    public AuctioneerServiceImpl(AuctioneerRepository auctioneerRepo) {
        this.auctioneerRepo = auctioneerRepo;
    }

    @Override
    public List<Auctioneer> getAllAutioneers() {
        return auctioneerRepo.findAll();
    }

    @Override
    public Auctioneer findById(int auctioneerId) {
        Optional<Auctioneer> result = auctioneerRepo.findById(auctioneerId);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("No auctineer with id " + auctioneerId);
        }
    }

    @Override
    public boolean deleteById(int auctioneerId) {
        if  (auctioneerRepo.findById(auctioneerId).isEmpty()) {
            return false;
        }
        auctioneerRepo.deleteById(auctioneerId);
        return true;
    }

    @Override
    public Auctioneer save(Auctioneer auctioneer) {
        return auctioneerRepo.save(auctioneer);
    }

}
