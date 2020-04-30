package com.geek.security.springmvc.service.impl;

import com.geek.security.springmvc.model.AuthenticationRequest;
import com.geek.security.springmvc.model.UserDto;
import com.geek.security.springmvc.service.AuthenticationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    // 用户信息。
    private Map<String, UserDto> userMap = new HashMap<>();

    {
        Set<String> authorities1 = new HashSet<>();
        authorities1.add("p1");
        Set<String> authorities2 = new HashSet<>();
        authorities2.add("p2");

        userMap.put("zhangsan", new UserDto("1010", "zhangsan", "123", "张三", "133111", authorities1));
        userMap.put("lisi", new UserDto("1011", "lisi", "456", "李四", "134553", authorities2));
    }

    // 根据账号查询用户信息。模拟用户查询。
    private UserDto getUserDto(String username) {
        return userMap.get(username);
    }

    /**
     * 认证信息。校验用户身份是否合法。
     *
     * @param authenticationRequest 用户认证请求。账号和密码。
     * @return 认证成功的用户信息。
     */
    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        // 校验参数是否为空。
        if (authenticationRequest == null
                || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())) {

            throw new RuntimeException("账号或密码为空。");
        }

        // 根据账号查询用户。（模拟）。
        UserDto userDto = getUserDto(authenticationRequest.getUsername());

        // 判断用户是否为空。
        if (userDto == null) {
            throw new RuntimeException("查询不到该用户。");
        }

        // 校验密码。
        if (!authenticationRequest.getPassword().equals(userDto.getPassword())) {
            throw new RuntimeException("账号或密码错误");
        }

//        return null;
        // 认证通过。返回用户身份信息。
        return userDto;
    }

}
