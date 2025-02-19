package com.example.online_auction_platform.dto.request.product;

import lombok.Data;

@Data
public class GetBiddingProductReqDto {
    
    int bidderId;
    int pageNumber;
    int pageSize;
}
