package com.Ida.controller;

import com.Ida.entity.User;
import com.Ida.service.UserService;

import com.Ida.util.MD5Utils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping({"/","/index.html"})
    public String index(){
        return "index";
    }


    @PostMapping("/toLogin")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

            model.addAttribute("code",0);
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
                model.addAttribute("msg","请输入用户名或者密码");
                return "login";
            }
            User dbUser = userService.findByUsername(username);
            if (null == dbUser) {
                model.addAttribute("msg", "该账号不存在，请检查后重试");
                return "login";
            }
            //验证密码是否正确
            String newPassword = MD5Utils.encode(password);
            if (!newPassword.equals(dbUser.getPassword())){
                model.addAttribute("msg","用户名/密码错误");
                return "login";
            }

            String remember = request.getParameter("remember");
            if (remember!=null) {
                //将登录用户信息保存到session中
                session.setAttribute("dbUser", dbUser);
                //保存cookie,实现自动登录
                Cookie cookie_username = new Cookie("cookie_username", username);
                //设置cookie的持久化时间，7天
                cookie_username.setMaxAge(7 * 24 * 60 * 60);
                // 设置为当前项目下都携带这个cookie
                cookie_username.setPath(request.getContextPath());
                // 向客户端发送cookie
                response.addCookie(cookie_username);
                model.addAttribute("code", 1);
            }
            model.addAttribute("msg", "登录成功");
            return "index";
        }

        //退出登录
    @RequestMapping("toLogout")
    public String logout(HttpSession session,HttpServletRequest request ,HttpServletResponse response){
        //删除session里面的用户信息
        session.removeAttribute("dbUser");
        //保存cookie,实现自动登录
        Cookie cookie_usrname = new Cookie("cookie_username","");
        //设置cookie的持久化时间，0
        cookie_usrname.setMaxAge(0);
        //设置当前项目下都携带这个cookie
        response.addCookie(cookie_usrname);
        return "login";
    }
}






















