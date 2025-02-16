package com.example.online_auction_platform.dto.request.product;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AddProductReqDto {
    
    int auctioneerId;
    String name;
    String description;
    int price;
    MultipartFile image;
    String location;
}
