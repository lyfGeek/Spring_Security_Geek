package com.geek.security.springmvc.controller;

import com.geek.security.springmvc.model.AuthenticationRequest;
import com.geek.security.springmvc.model.UserDto;
import com.geek.security.springmvc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 登录功能。
     *
     * @param authenticationRequest
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", produces = {"text/plain; charset=utf-8"})// 返回纯文本。
    public String login(AuthenticationRequest authenticationRequest, HttpSession session) {
        UserDto userDto = authenticationService.authentication(authenticationRequest);

        // 存入 session。
        session.setAttribute(UserDto.SESSION_USER_KEY, userDto);

        return userDto.getUsername() + " 登录成功。";
    }

    /**
     * 退出功能。
     *
     * @param session
     * @return
     */
    @GetMapping(value = "/logout", produces = {"text/plain; charset=utf-8"})
    public String logout(HttpSession session) {
        session.invalidate();
        return "退出成功。";
    }

    /**
     * 测试资源 1。
     *
     * @param session
     * @return
     */
    @GetMapping(value = "r/r1", produces = {"text/plain; charset=utf-8"})
    public String r1(HttpSession session) {

        String fullname = null;
        Object object = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (object == null) {// 没有登录就匿名访问。
            fullname = "匿名";
        } else {
            UserDto userDto = (UserDto) object;
            fullname = userDto.getFullname();
        }
        return fullname + " 访问资源 r1";
    }

    /**
     * 测试资源 2。
     *
     * @param session
     * @return
     */
    @GetMapping(value = "r/r2", produces = {"text/plain; charset=utf-8"})
    public String r2(HttpSession session) {
        String fullname = null;
        Object object = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (object == null) {
            fullname = "匿名";
        } else {
            UserDto userDto = (UserDto) object;
            fullname = userDto.getFullname();
        }
        return fullname + " 访问资源 r2";
    }
}
