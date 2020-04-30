package com.geek.security.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration// applicationContext.xml。
@ComponentScan(basePackages = "com.geek.security.spring",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)}
        // 排除 web 层 Controller。
)
public class ApplicationConfig {

    // 在此配置除了 Controller 的其他 Bean。eg. 数据库连接池，事务管理器，业务 Bean 等。
}
