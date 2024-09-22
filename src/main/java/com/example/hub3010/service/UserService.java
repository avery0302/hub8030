package com.example.hub3010.service;

import com.example.hub3010.mapper.UserMapper;
import com.example.hub3010.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String getFirstUser() {
        return userMapper.getFirstUser();
    }

    // 方法：添加键值对，并设置过期时间为10分钟
    public void addSession(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value, 10, TimeUnit.MINUTES);
            logger.info("Session added with key: {} and value: {}", key, value);
        } catch (Exception e) {
            logger.error("Failed to add session to Redis: {}", e.getMessage());
        }
    }

    // 方法：检查Redis中是否存在指定的key
    public boolean checkSession(String key) {
        try {
            boolean exists = Boolean.TRUE.equals(redisTemplate.hasKey(key));
            logger.info("Session check for key: {} returned: {}", key, exists);
            return exists;
        } catch (Exception e) {
            logger.error("Failed to check session in Redis: {}", e.getMessage());
            return false;
        }
    }
}