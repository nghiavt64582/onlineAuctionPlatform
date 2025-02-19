package com.example.online_auction_platform.dto.request.bidden_price;

import lombok.Data;

@Data
public class BiddingReqDto {
    int bidderId;
    int productId;
    int price;
}
