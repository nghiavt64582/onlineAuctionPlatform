package com.example.online_auction_platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.online_auction_platform.entities.Product;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RedisService {
    
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public Object getObject(Object key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void cacheObject(Object key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void cachePage(String key, Page<? extends Object> page) {
        try {
            String json = objectMapper.writeValueAsString(page);
            redisTemplate.opsForValue().set(key, json);
        } catch (Exception e) {
            System.out.println("Cache page error");
        }
    }

    public Page<? extends Object> getPage(String key) {
        Object data = redisTemplate.opsForValue().get(key);
        String json = (String) data;
        if (json != null) {
            JavaType type = objectMapper.getTypeFactory().constructParametricType(PageImpl.class, Product.class);
            try {
                return objectMapper.readValue(json, type);
            } catch (Exception e) {
                System.out.println("Get page error");
                return Page.empty();
            }
        }
        System.out.println("No data for key");
        return Page.empty();
    }
}