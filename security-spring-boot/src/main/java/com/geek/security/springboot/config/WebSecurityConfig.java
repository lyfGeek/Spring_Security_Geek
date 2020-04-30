package com.geek.security.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全配置。
 * 用户信息、密码编码器、安全拦截机制。
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 定义用户信息服务（查询用户信息）。查询数据库或内存方式。
/*
    @Bean
    public UserDetailsService userDetailsService() {
        // 基于内存的方式。
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
        inMemoryUserDetailsManager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
        return inMemoryUserDetailsManager;
    }
*/

    // 密码编码器。（密码比对方式）。
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 不加密。
        return NoOpPasswordEncoder.getInstance();
    }
*/

    // 安全拦截机制。（关键）。
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http
                .authorizeRequests()
                // 拦截实现授权。
                .antMatchers("/r/r1").hasAuthority("p1")
                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/**").authenticated()// 此路径 /r/** 要求认证。
                .anyRequest().permitAll()// 除了 /r/**，其他放行。
                .and()
                .formLogin()// 允许表单登录。
                .successForwardUrl("/login-success");// 自定义登录成功的页面地址。
    }
*/

    // 安全拦截机制。（关键）。
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.csrf().disable()// 屏蔽 CSRF 控制。让 Spring Security 不再限制 CSRF。
                .authorizeRequests()

//                .antMatchers("/admin/**").hasRole("ADMIN")// 这里如果通过了，后面就不再执行。
//                .antMatchers("/admin/login").permitAll()
                // ↓ ↓ ↓ 顺序很重要。具体放前面。

//                .antMatchers("/admin/login").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")

                // 拦截实现授权。
//                .antMatchers("/r/r1").hasAuthority("p1")
//                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/**").authenticated()// 此路径 /r/** 要求认证。
                .anyRequest().permitAll()// 除了 /r/**，其他放行。
                .and()
                .formLogin()// 允许表单登录。
                .loginPage("/login-view")// 指定我们自己的登录页面，Spring Security 以重定向的方式跳转到 /login-view。
                .loginProcessingUrl("/login")// 指定登录处理的 url，也就是用户名、密码表单提交的目的路径。
                .successForwardUrl("/login-success")// 自定义登录成功的页面地址。
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)// 会话何时创建。
                // 自定义退出。
                .and()
                .logout()// 提供系统退出支持。
                .logoutUrl("/logout")// 设置触发退出操作的 url。默认是 /logout。
                .logoutSuccessUrl("/logout-view?logout")// 退出之后跳转的 url。默认是 /logout-view?logout。
//                .logoutSuccessHandler(logoutSuccessHandler)// 定制的 LogoutSuccessHandler。用于实现用户退出成功时的处理。如果指定了这个选项那么 .logoutSuccessUrl() 的设置会被覆盖。
//                .addLogoutHandler(logoutSuccessHandler)// 添加一个 LogoutHandler，用于实现用户退出时的清理工作。默认 SecurityContextLogoutHandler 会被添加为最后一个 LogoutHandler。
                .invalidateHttpSession(true)// 指定是否在退出时让 HttpSession 无效。默认为 true。

        // 如果要让 logout 在 GET 请求下生效，必须关闭防止 CSRF 攻击 csrf().disable()。
        // 如果开启了 CSRF，必须使用 post 方式请求 /logout。

        // 当退出操作执行时。
        // 使 HTTP Session 无效。
        // 清除 SecurityContextHolder。
        // 跳转到 “/logout-view?logout”。
        ;
    }
}
