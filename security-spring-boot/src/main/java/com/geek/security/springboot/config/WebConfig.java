package com.geek.security.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Spring Boot 的自动装配机制，不需要 @EnableWebMvc 和 @ComponentScan。

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/login-view");
        registry.addViewController("/login-view").setViewName("login");

        // Spring Security 提供的。
//        registry.addViewController("/").setViewName("redirect:/login");
    }
}
