package com.example.online_auction_platform.services.authority;

import org.springframework.stereotype.Service;

import com.example.online_auction_platform.entities.Authority;

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
