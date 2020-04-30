package com.geek.security.springmvc.init;

import com.geek.security.springmvc.config.ApplicationConfig;
import com.geek.security.springmvc.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*
    public interface WebApplicationInitializer {
        void onStartup(ServletContext var1) throws ServletException;
    }
    只要最终实现了此接口，就能在容器启动时执行类中的方法。
    */

    // 相当于加载 Spring 容器。（applicationContext.xml）。
    @Override
    protected Class<?>[] getRootConfigClasses() {
//        return new Class[0];
        return new Class[]{ApplicationConfig.class};
    }

    // 相当于加载 SpringMVC.xml。servletContext。
    @Override
    protected Class<?>[] getServletConfigClasses() {
//        return new Class[0];
        return new Class[]{WebConfig.class};
    }

    // url-mapping。
    @Override
    protected String[] getServletMappings() {
//        return new String[0];
        return new String[]{"/"};
    }
}
