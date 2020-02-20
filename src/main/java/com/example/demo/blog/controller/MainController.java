package com.example.demo.blog.controller;

import com.example.demo.blog.domain.Authority;
import com.example.demo.blog.domain.User;
import com.example.demo.blog.service.AuthorityService;
import com.example.demo.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private static final Long ROLE_USER_AUTHORITY_ID = 2L;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserService userService;

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

    /**
     * 注册用户
     */
    @PostMapping("/register")
    public String registerUser(User user) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID).get());
        user.setAuthorities(authorities);

        userService.registerUser(user);
        return "redirect:/login";
    }

}
