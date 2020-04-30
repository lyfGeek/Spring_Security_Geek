package com.geek.security.spring.init;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SpringSecurityApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SpringSecurityApplicationInitializer() {
        // 若当前没有使用 Spring 或 Spring MVC。
//        super(WebSecurityConfig.class);
    }
}
