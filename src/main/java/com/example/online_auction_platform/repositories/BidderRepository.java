package com.example.online_auction_platform.repositories;

import java.util.Optional;

import com.example.online_auction_platform.entities.Bidder;

public interface BidderRepository extends UserRepository {

    Bidder save(Bidder bidder);

    Optional<Bidder> findById(int bidderId);
}
