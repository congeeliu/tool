package com.usts.backend.controller;

import com.usts.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/register/")
    private Map<String, String> register(@RequestParam Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");
        String passwordConfirm = data.get("passwordConfirm");
        return userService.register(username, password, passwordConfirm);
    }

    @PostMapping("/user/login/")
    private Map<String, String> login(@RequestParam Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");
        return userService.login(username, password);
    }

    @GetMapping("/user/info/")
    private Map<String, String> getInfo() {
        return userService.getInfo();
    }
}
