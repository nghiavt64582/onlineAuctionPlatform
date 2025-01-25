package com.example.online_auction_platform.services.bidder;

import java.util.Optional;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.online_auction_platform.entities.Bidder;

import jakarta.transaction.Transactional;

public interface BidderRepository extends PagingAndSortingRepository<Bidder, Integer> {

    // cached plan
    Optional<Bidder> findByBidderId(int bidder_id); 

    // stored procedure
    @Procedure("GetBidderById")
    @Transactional
    Optional<Bidder> findByBidderIdProcedure(int p_bidder_id);

    Bidder save(Bidder bidder);
}
