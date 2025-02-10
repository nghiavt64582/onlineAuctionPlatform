package com.example.online_auction_platform.services;

import org.springframework.stereotype.Service;

import com.example.online_auction_platform.entities.Authority;
import com.example.online_auction_platform.repositories.AuthorityRepository;

@Service
public class AuthorityService {

    private AuthorityRepository authorityRepo;

    public AuthorityService(AuthorityRepository authorityRepo) {
        this.authorityRepo = authorityRepo;
    }

    public Authority save(Authority authority) {
        return authorityRepo.save(authority);
    }
    
}
