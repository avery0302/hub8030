package com.example.hub3010.controller;

import com.example.hub3010.model.User;
import com.example.hub3010.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/first")
    public String getFirstUser() {
        return userService.getFirstUser();
    }

    // 接口：添加键值对，模拟用户登录或会话创建
    @PostMapping("/session/create")
    public String createSession(@RequestParam String key, @RequestParam String value) {
        userService.addSession(key, value);
        return "Session created with key: " + key;
    }

    // 接口：查询Redis中是否存在指定的key，模拟会话检查
    @GetMapping("/session/check")
    public String checkSession(@RequestParam String key) {
        boolean exists = userService.checkSession(key);
        return exists ? "Session exists for key: " + key : "Session does not exist for key: " + key;
    }
}