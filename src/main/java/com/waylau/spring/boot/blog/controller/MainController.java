package com.waylau.spring.boot.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-erro")
    public String loginError(Model model) {
        model.addAttribute("loginErro", true);
        model.addAttribute("erroMsg", "登录失败, 用户名或密码错误");
        return "login";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }


}
