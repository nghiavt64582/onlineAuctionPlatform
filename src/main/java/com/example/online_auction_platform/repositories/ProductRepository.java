package com.example.online_auction_platform.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.online_auction_platform.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    public Page<Product> findAll(Pageable pageable);

    public Page<Product> findByAuctioneer_Id(int auctioneerId, Pageable pageable);

    public Optional<Product> findByImageUrl(String imageUrl);

    public List<Product> findByCategories_NameIn(List<String> categoryNames);

    @Query("SELECT p FROM Product p WHERE p.id IN (SELECT bp.product.id FROM BiddenPrice bp WHERE bp.bidder.id = :bidderId)")
    public List<Product> findBiddingProductByBidderId(@Param("bidderId") int bidderId, Pageable pageable);
    

}