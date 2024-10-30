package com.example.onlineAuctionPlatform.enums;

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
