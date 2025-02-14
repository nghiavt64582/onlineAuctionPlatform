package com.example.online_auction_platform.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online_auction_platform.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    public Page<Product> findAll(Pageable pageable);

    public Page<Product> findByAuctioneer_Id(int auctioneerId, Pageable pageable);

    public Optional<Product> findByImageUrl(String imageUrl);

}
