package com.Ida.dao;
import com.Ida.entity.User;

public interface UserDao  {

    /**
     * 根据用户名查看用户
     */
    public User findByUsername(String userName);

    /**
     * 根据用户名查找对应密码
     */
    public String findPassword(String username);

}
