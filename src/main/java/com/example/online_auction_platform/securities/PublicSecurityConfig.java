package com.example.online_auction_platform.securities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(1) // Highest priority
public class PublicSecurityConfig {

    @Bean
    public SecurityFilterChain publicSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/avatars/**").permitAll();
            auth.requestMatchers("/").permitAll();
        });
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
