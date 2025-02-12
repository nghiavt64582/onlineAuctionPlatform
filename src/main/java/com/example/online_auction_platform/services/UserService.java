package com.example.online_auction_platform.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.online_auction_platform.entities.User;
import com.example.online_auction_platform.repositories.UserRepository;

@Service
public class UserService {

    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public User findByUsername(String username) {
        Optional<User> result = userRepo.findByUsername(username);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }
    
}
