package com.geek.security.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @author geek
 */
@Data
@AllArgsConstructor
public class UserDto {

    // 存放登录用户信息的 key。
    public static final String SESSION_USER_KEY = "_user";
    // 用户身份信息。
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

    // 用户权限。
    private Set<String> authorities;

}
