package com.example.online_auction_platform.entities;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    
    @Id
    @Size(max = 25, min = 5)
    @Pattern(regexp = "^[a-zA-Z0-9.]+$")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "role")
    private String role;

    @Column(name = "enabled")
    private int enabled;
    
    public User(String username, String password, int enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public int getEnabled() {
        return enabled;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
