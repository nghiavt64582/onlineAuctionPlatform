package com.example.onlineAuctionPlatform.services.authority;

import org.springframework.stereotype.Service;

import com.example.onlineAuctionPlatform.entities.Authority;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private AuthorityRepository authorityRepo;

    public AuthorityServiceImpl(AuthorityRepository authorityRepo) {
        this.authorityRepo = authorityRepo;
    }

    @Override
    public Authority save(Authority authority) {
        return authorityRepo.save(authority);
    }
    
}
