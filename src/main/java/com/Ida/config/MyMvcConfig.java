package com.Ida.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/register.html").setViewName("register");
        //url输入 login.html 就能访问到登录页面
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/findPassword.html").setViewName("findPassword");
    }
}
