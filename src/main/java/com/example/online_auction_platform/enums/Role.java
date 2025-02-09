package com.example.online_auction_platform.enums;

public enum Role {
    ADMIN(0),
    AUCTIONEER(1),
    BIDDER(2);
    
    private int code;

    public int getCode() {
        return code;
    }

    Role(int code) {
        this.code = code;
    }
}
