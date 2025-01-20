package com.example.online_auction_platform.helpers;

import com.example.online_auction_platform.entities.User;

public class UserHelper {
    
    public static String updateUserPasswordEncrypt(String password) {
        if (password.startsWith("{noop}")) {
            return password;
        } else {
            return "{noop}" + password;
        }
    }

    public static boolean updateUserPasswordEncrypt(User user) {
        String newPassword = updateUserPasswordEncrypt(user.getPassword());
        if (newPassword.equals(user.getPassword())) {
            return false;
        } else {
            user.setPassword(newPassword);
            return true;
        }
    }

}
