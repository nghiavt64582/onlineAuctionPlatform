package com.example.online_auction_platform.dto.request.product;

import java.util.List;

import lombok.Data;

@Data
public class GetOpenProductReqDto {
    
    int startId;
    int bidderId;
    int pageNumber;
    int pageSize;
    List<String> categories;
}
