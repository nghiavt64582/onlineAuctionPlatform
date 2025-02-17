package com.example.online_auction_platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.online_auction_platform.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
