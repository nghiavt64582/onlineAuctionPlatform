package com.example.online_auction_platform.securities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(2) // Highest priority
public class AdminSecurityConfig {
    
    @Bean
    public SecurityFilterChain adminSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.GET, "/admin/users/**").hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.GET, "/admin/users").hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.POST, "/admin/authorities").hasRole("ADMIN");
            auth.anyRequest().permitAll();
        });
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
