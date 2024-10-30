package com.example.onlineAuctionPlatform.enums;

public enum ProductState {
    
    PENDING(0),
    BIDDING(1),
    SOLD(2);

    private int code;

    public int getCode() {
        return code;
    }

    ProductState(int code) {
        this.code = code;
    }
}
