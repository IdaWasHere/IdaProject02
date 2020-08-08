package com.Ida.service;

import com.Ida.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface UserService  {

    /**
     * 根据用户名找用户
     */
    User findByUsername(String username);

    /**
     * 根据用户名查找对应密码
     */
    public String findPassword(String username);

}
