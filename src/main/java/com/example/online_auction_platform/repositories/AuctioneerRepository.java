package com.example.online_auction_platform.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.example.online_auction_platform.entities.Auctioneer;

public interface AuctioneerRepository extends UserRepository {
    public Optional<Auctioneer> findById(int id);

    @Query(value = "SELECT a FROM Auctioneer a")
    public Page<Auctioneer> findAllAuctioneers(Pageable pageable);
}
