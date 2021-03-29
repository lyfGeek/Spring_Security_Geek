package com.geek.security.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author geek
 */
@Configuration// SpringMVC.xml。
@EnableWebMvc
@ComponentScan(basePackages = "com.geek.security.spring",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)}
)
public class WebConfig implements WebMvcConfigurer {
/*
    @Autowired
    private SimpleAuthenticationInterceptor simpleAuthenticationInterceptor;

    // 配置拦截器。
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(simpleAuthenticationInterceptor).addPathPatterns("/r/**");// 指定。为了不拦截登录页面。
    }
*/
// Spring Security 框架提供了拦截器，就不用我们自己写了。

    // 视图解析器。
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    // 默认 url 根路径跳转到 /login。此 url 为 Spring Security 提供。
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/login");
    }

}
