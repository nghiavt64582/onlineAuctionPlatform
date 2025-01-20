package com.example.online_auction_platform.enums;

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
