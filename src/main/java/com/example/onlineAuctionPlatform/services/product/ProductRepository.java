package com.example.onlineAuctionPlatform.services.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineAuctionPlatform.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
