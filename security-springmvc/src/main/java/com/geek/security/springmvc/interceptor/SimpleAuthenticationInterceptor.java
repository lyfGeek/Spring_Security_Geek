package com.geek.security.springmvc.interceptor;

import com.geek.security.springmvc.model.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {
    /**
     * 校验用户请求的 url 是否在用户的权限范围内。
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取用户身份信息。（从 Session 中获取）。
        Object obj = request.getSession().getAttribute(UserDto.SESSION_USER_KEY);
        if (obj == null) {
            // 没有认证，提示登录。// 匿名访问就失效了。
            writeContent(response, "请登录。");
        }
        UserDto userDto = (UserDto) obj;
        // 请求的 url。
        String requestURI = request.getRequestURI();

        // todo。如果没有登录，这里会报空指针异常。
        if (userDto.getAuthorities().contains("p1") && requestURI.contains("/r/r1")) {
            return true;// 放行。
        }
        if (userDto.getAuthorities().contains("p2") && requestURI.contains("/r/r2")) {
            return true;// 放行。
        }
        writeContent(response, "没有权限。");

        return false;
    }

    private void writeContent(HttpServletResponse response, String s) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(s);
        writer.close();
//        response.resetBuffer();
    }

}
