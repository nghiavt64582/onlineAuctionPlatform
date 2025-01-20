package com.example.online_auction_platform.enums;

public enum Role {
    ADMIN(0),
    MONITOR(1),
    AUCTIONEER(2),
    BIDDER(3);
    
    private int code;

    public int getCode() {
        return code;
    }

    Role(int code) {
        this.code = code;
    }
}
