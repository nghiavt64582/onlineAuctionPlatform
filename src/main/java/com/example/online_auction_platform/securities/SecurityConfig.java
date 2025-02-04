package com.example.online_auction_platform.securities;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public UserDetailsManager userDetailManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        // define query to retrive a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
            "select username, password, enabled from user where username=?"
            );

        // define query to retrive role by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "select username, authority from authority where username=?"
            );
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println("SecurityConfig.filterChain");
        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/", "/login").permitAll();
            auth.requestMatchers("/ws/**").permitAll();
            auth.requestMatchers("/ws").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/admin/users/**").hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.GET, "/admin/users/**").hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.GET, "/admin/users").hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.POST, "/admin/authorities").hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.GET, "/auctioneer/products").hasRole("AUCTIONEER");
            auth.requestMatchers(HttpMethod.PUT, "/auctioneer/auctioneer").hasRole("AUCTIONEER");
            auth.requestMatchers(HttpMethod.POST, "/auctioneer/products").hasRole("AUCTIONEER");
            auth.requestMatchers(HttpMethod.GET, "/auctioneer/products/**").hasRole("AUCTIONEER");
            auth.requestMatchers(HttpMethod.GET, "/hello").hasRole("AUCTIONEER");
            auth.requestMatchers(HttpMethod.GET, "/auctioneer/productsByAuctioneerId/**").hasRole("AUCTIONEER");
            auth.requestMatchers(HttpMethod.PUT, "/bidder/bidder").hasRole("BIDDER");
            auth.requestMatchers(HttpMethod.POST, "/bidder/bidden-price").hasRole("BIDDER");
            auth.requestMatchers(HttpMethod.GET, "/info").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/bidder/bidder-test").hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.PUT, "/bidder/bidder").hasRole("BIDDER");
            auth.requestMatchers(HttpMethod.POST, "/bidder/bidden-price").hasRole("BIDDER");
            auth.requestMatchers(HttpMethod.GET, "/bidder/bidder-test").hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.GET, "/product/products").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/product/products/**").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/product/products/id").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/product/products/imageUrl").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/product/test").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/api/auth/google").permitAll();
        }
        );
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }
}
