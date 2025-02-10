package com.example.online_auction_platform.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class Bidder extends User {
    
    @Id
    private int id;

    @Column(name = "cash")
    private int cash;
   
    public void updateCreatedDate() {
        if (this.createdDate == null) {
            this.createdDate = LocalDateTime.now();
        }
    }

}
