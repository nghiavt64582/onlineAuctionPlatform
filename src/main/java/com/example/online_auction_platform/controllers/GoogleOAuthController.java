package com.example.online_auction_platform.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/auth")
public class GoogleOAuthController {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${google.oauth.client.id}")
    private static String CLIENT_ID;

    @Value("${google.oauth.client.secret}")
    private static String CLIENT_SECRET;

    @Value("${google.oauth.redirect.uri}")
    private static String REDIRECT_URI;

    @Value("${google.oauth.token.uri}")
    private static String TOKEN_URL;
    
    @Value("${google.oauth.userinfo.uri}")
    private static String USER_INFO_URL;

    @GetMapping("/google")
    public ResponseEntity<?> handleGoogleCallback(@RequestParam("code") String code) {
        String accessToken = exchangeCodeForAccessToken(code);
        Map<String, Object> userInfo = fetchUserInfo(accessToken);
        return ResponseEntity.ok(userInfo);
    }

    private String exchangeCodeForAccessToken(String code) {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", CLIENT_ID);
        params.put("client_secret", CLIENT_SECRET);
        params.put("code", code);
        params.put("redirect_uri", REDIRECT_URI);
        params.put("grant_type", "authorization_code");

        ResponseEntity<Map> response = restTemplate.postForEntity(TOKEN_URL, params, Map.class);
        return (String) response.getBody().get("access_token");
    }

    private Map<String, Object> fetchUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(USER_INFO_URL, HttpMethod.GET, entity, Map.class);
        return response.getBody();
    }
}
