package com.example.online_auction_platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online_auction_platform.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    public Product findByImageUrl(String imageUrl);

}
