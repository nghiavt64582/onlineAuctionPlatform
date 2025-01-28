package com.example.online_auction_platform.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@NamedQueries({
    @NamedQuery(name="Bidder.getBidderById", query = "SELECT b FROM Bidder b WHERE b.id = :bidder_id")
})
@Data
@AllArgsConstructor
public class Bidder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private int cash;
    private String username;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") 
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "created_date")
    public LocalDateTime createdDate;

    public Bidder() {
        this.id = 10;
        this.name = "Nghia";
        this.email = "nghia64582@gmail.com";
    }

    public Bidder(
        int id, 
        String name, 
        String email, 
        int cash,
        String username,
        int createdDate
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cash = cash;
        this.username = username;
        this.createdDate = LocalDateTime.now();
    }

    public Bidder(String username) {
        this.username = username;
        this.cash = 0;
        this.createdDate = LocalDateTime.now();
    }
   
    public void updateCreatedDate() {
        if (this.createdDate == null) {
            this.createdDate = LocalDateTime.now();
        }
    }


}
