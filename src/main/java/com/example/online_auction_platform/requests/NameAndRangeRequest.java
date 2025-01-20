package com.example.online_auction_platform.requests;

public class NameAndRangeRequest {

    private int min;
    private int max;
    private String name;
    private String method;

    public String getMethod() {
        return method;
    }

    public String getName() {
        return name;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
