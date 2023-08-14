package com.usts.backend.service;

import java.util.Map;

public interface UserService {
    Map<String, String> register(String username, String password, String passwordConfirm);

    Map<String, String> login(String username, String password);

    Map<String, String> getInfo();
}
