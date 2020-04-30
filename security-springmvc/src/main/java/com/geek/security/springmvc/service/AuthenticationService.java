package com.geek.security.springmvc.service;

import com.geek.security.springmvc.model.AuthenticationRequest;
import com.geek.security.springmvc.model.UserDto;

public interface AuthenticationService {

    /**
     * 认证信息。校验用户身份是否合法。
     *
     * @param authenticationRequest 用户认证请求。账号和密码。
     * @return 认证成功的用户信息。
     */
    UserDto authentication(AuthenticationRequest authenticationRequest);
}
