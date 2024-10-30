package com.example.onlineAuctionPlatform.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Authority {
    
    @Id
    private String username;
    private String authority;

    public Authority() {
    }

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthority() {
        return authority;
    }
}
