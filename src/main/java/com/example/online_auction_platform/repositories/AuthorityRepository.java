package com.example.online_auction_platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online_auction_platform.entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
        
}
