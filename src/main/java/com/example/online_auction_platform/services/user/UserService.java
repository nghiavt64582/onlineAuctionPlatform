package com.example.online_auction_platform.services.user;

import com.example.online_auction_platform.entities.User;

import java.util.List;

public interface UserService {
    
    List<User> getAllUsers();

    User findByUsername(String username);

    // boolean deleteById(int userId);

    User save(User user);
}
