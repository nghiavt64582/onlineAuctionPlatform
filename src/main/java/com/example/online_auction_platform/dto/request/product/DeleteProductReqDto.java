package com.example.online_auction_platform.dto.request.product;

import lombok.Data;

@Data
public class DeleteProductReqDto {
    
    int auctioneerId;
    int productId;
}
