package com.example.online_auction_platform.dto.request.product;

import lombok.Data;

@Data
public class UpdateProductRequestDto {
    
    int auctioneerId;
    int productId;
    String productName;
    int startPrice;
    String location;
}
