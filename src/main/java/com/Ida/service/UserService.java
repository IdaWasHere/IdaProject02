package com.Ida.service;

import com.Ida.dao.UserDao;
import com.Ida.entity.Result;
import com.Ida.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface UserService  {

    /**
     * 添加/修改用户信息
     */
     void register(User user);

    /**
     * 根据用户名找用户
     */
    User findByUsername(String username);

    /**
     * 根据用户名查找对应密码
     */
    public String findPassword(String username);

    /**
     * 发送邮件
     */
    public void sendMail(String to,String subjet,String content);






    /** @Autowired
     private UserDao userDao;

     public Result regist(User user){
         Result result = new Result();
         result.setSuccess(false);
         result.setDetail(null);

         try {
             User existUser = userDao.findByUserName(user.getUsername());
             if(existUser != null){
                 //如果用户名已存在
                 result.setMsg("用户名已存在");
             }else{
                 userDao.regist(user);
                 //System.out.println(user.getId());
                 result.setMsg("注册成功");
                 result.setSuccess(true);
                 result.setDetail(user);            }
         } catch (Exception e) {
             result.setMsg(e.getMessage());
             e.printStackTrace();
         }
         return result;
     }*/
}
