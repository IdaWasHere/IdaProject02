package com.Ida.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/index.html").setViewName("index");
    }

    @Autowired
    private LoginInterceptor loginHandlerInterceptor;
    //注册拦截器
    @Override
     public void addInterceptors(InterceptorRegistry registry) {
       InterceptorRegistration ir = registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**").excludePathPatterns("/login.html","/toLogin","/");
        //拦截路径
      // ir.addPathPatterns("/*");
        //不拦截路径......
/*       List<String> irs = new ArrayList<>();
        irs.add("/login.html");
        ir.excludePathPatterns(irs);*/
    }
}
