package com.example.onlineAuctionPlatform.exceptions;

public class AuctionExceptionResponse {
    
    private int code;
    private String message;
    private Long timestamp;
    
    public AuctionExceptionResponse(int code, String message, Long timestamp) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public Long getTimestamp() {
        return timestamp;
    }
}
