package com.Ida.controller;

import com.Ida.entity.User;
import com.Ida.service.UserService;
import com.Ida.util.FindPwdUtil;
import com.Ida.util.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //进行注册   与register.html的action对应
    @PostMapping("/register")
    public String register(User user, @RequestParam("username") String username, Model model) {

        User findUser = userService.findByUsername(username);

        if (findUser == null) {
            userService.register(user);
            //跳转到登录页面
            return "login";
        } else {
            model.addAttribute("registermsg", "该用户名已被注册！");
            return "register";
        }

    }

    /**
     * //这个方法没能带上 提示消息：已被注册
     * // 进入注册页面：localhost:8080/user/toRegister
     *
     * @GetMapping("/toRegister") public String toRegister(){
     * return "/register";
     * }
     */

    @PostMapping("/login")
    public String login(
            //@RequestParam为了保险接收参数 Model model为了回显数据
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model,
            HttpServletRequest request) {

        String findPassword = userService.findPassword(username);

        String code = request.getParameter("code");

        //获取session中的验证码
        String random_code = (String) request.getSession().getAttribute("randomCode");

        //具体业务
        if (!StringUtils.isEmpty(username) && findPassword.equals(password) ) {
            if (!StringUtils.isEmpty(code) && code.equals(random_code)) {
                //登陆成功，跳转到首页
                return "index";
            } else {
                model.addAttribute("msg", "验证码错误！");
                return "login";
            }
        } else {
            //告诉用户，登录失败
            model.addAttribute("msg", "用户名/密码错误！");
            return "login";
        }
    }

    @PostMapping("/forgetPassword")
    public String forgetPassword(HttpServletRequest request,Model model){
        String veritycode = request.getParameter("veritycode");
        String verity_code = (String) request.getSession().getAttribute("verity");
        if(!StringUtils.isEmpty(veritycode) && veritycode.equals(verity_code)){
            //验证成功 ， 跳转到首页
            return "index";
        }else{
            model.addAttribute("verityfail","验证码错误");
            return "findPassword";
        }

    }
}

























