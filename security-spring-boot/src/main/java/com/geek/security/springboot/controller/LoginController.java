package com.geek.security.springboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value = "/login-success", produces = {"text/plain; charset=UTF-8"})
    public String loginSuccess() {
        // 提示登陆成功的具体用户名。
        return getUsername() + "登录成功。";
    }

    /**
     * 测试资源 1。
     *
     * @return
     */
    @GetMapping(value = "/r/r1", produces = {"text/plain; charset=UTF-8"})
//    @PreAuthorize("isAnonymous()")
    @PreAuthorize("hasAuthority('p1')")// 拥有 p1 权限才可以访问。
//    @PreAuthorize("hasAnyAuthority('p1', 'p2')")
    public String r1() {
        return getUsername() + "访问资源 1。";
    }

    /**
     * 测试资源 2。
     *
     * @return
     */
    @GetMapping(value = "/r/r2", produces = {"text/plain; charset=UTF-8"})
    @PreAuthorize("hasAuthority('p2')")// 拥有 p2 权限才可以访问。
//    @PreAuthorize("hasAuthority('p_transfer') and hasAnyAuthority('p_read-account')")
    public String r2() {
        return getUsername() + "访问资源 2。";
    }

    /**
     * 获取当前用户信息。
     *
     * @return
     */
    private String getUsername() {

        String username = null;

        // 当前登陆成功的用户身份。
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 用户身份。
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            username = "匿名";
        }
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        return username;
    }
}
