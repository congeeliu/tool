package com.usts.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.usts.backend.mapper.UserMapper;
import com.usts.backend.pojo.User;
import com.usts.backend.service.UserService;
import com.usts.backend.service.impl.utils.UserDetailsImpl;
import com.usts.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private static final String salt = "jnszstm";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> register(String username, String password, String passwordConfirm) {
        Map<String, String> map = new HashMap<>();

        if (username == null || username.length() == 0) {
            map.put("errorMessage", "用户名不能为空");
            return map;
        }

        if (username.contains(" ")) {
            map.put("errorMessage", "用户名不能包含空格");
            return map;
        }

        if (username.length() > 20) {
            map.put("errorMessage", "用户名长度不能超过20");
            return map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        if (userMapper.selectOne(queryWrapper) != null) {
            map.put("errorMessage", "用户名已存在");
            return map;
        }

        if (password == null || password.length() == 0) {
            map.put("errorMessage", "密码不能为空");
            return map;
        }

        if (password.contains(" ")) {
            map.put("errorMessage", "密码不能包含空格");
            return map;
        }

        if (!passwordConfirm.equals(password)) {
            map.put("errorMessage", "密码不一致");
            return map;
        }

//        String str = password + salt;
//        String md5 = DigestUtils.md5DigestAsHex(str.getBytes());
        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(null, username, encodedPassword);
        userMapper.insert(user);
        map.put("errorMessage", "success");

        return map;
    }

    @Override
    public Map<String, String> login(String username, String password) {
        Map<String, String> map = new HashMap<>();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User userCheck = userMapper.selectOne(queryWrapper);

        if (userCheck == null) {
            map.put("errorMessage", "用户不存在");
            return map;
        }

        if (!passwordEncoder.matches(password, userCheck.getPassword())) {
            map.put("errorMessage", "密码错误");
            return map;
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();
        User user = loginUser.getUser();

        String jwt = JwtUtil.createJWT(user.getId().toString());

        map.put("errorMessage", "success");
        map.put("token", jwt);

        return map;
    }

    @Override
    public Map<String, String> getInfo() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();
        map.put("errorMessage", "success");
        map.put("id", user.getId().toString());
        map.put("username", user.getUsername());

        return map;
    }
}
