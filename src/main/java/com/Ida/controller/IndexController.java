package com.Ida.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/*根路径*/
//记得导入thymeleaf依赖，来识别
@Controller
public class IndexController {
/* 首页*/
    @RequestMapping({"/","index.html"})
    public String index(){
        return "index";
    }
}
