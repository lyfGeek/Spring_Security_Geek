package com.geek.security.springmvc.model;

import lombok.Data;

@Data
public class AuthenticationRequest {
    // 认证请求的参数。

    private String username;

    private String password;
}
