package com.example.onlineAuctionPlatform.services.user;

import com.example.onlineAuctionPlatform.entities.User;

import java.util.List;

public interface UserService {
    
    List<User> getAllUsers();

    User getByUsername(String username);

    // boolean deleteById(int userId);

    User save(User user);
}
