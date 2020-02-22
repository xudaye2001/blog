package com.example.demo.blog.controller;

import com.example.demo.blog.domain.User;
import com.example.demo.blog.service.UserService;
import com.example.demo.blog.service.UserServiceImpl;
import com.example.demo.blog.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/u")
public class UserspaceController {

    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username") String username) {
        System.out.print("username:" + username);
        return "/userspace/u";
    }

    @GetMapping("/{username}/lobogs")
    public String listBlogOrder(@PathVariable("username") String username,
                                @RequestParam(value = "order", required = false, defaultValue = "new")
                                        String order,
                                @RequestParam(value = "category", required = false)
                                        Long category,
                                @RequestParam(value = "keyword", required = false)
                                        String keyword) {
        if (category != null) {
            System.out.print("category:" + category);
            System.out.print("selflink:" + "redirect:/u/" + username + "blogs?category=" + category);
            return "/userspace/u";
        } else if (keyword != null && !keyword.isEmpty()) {
            System.out.print("keyword:" + keyword);
            System.out.print("selflink:" + "redirect:/u/" + username + "blogs>keyword=" + keyword);
            return "/userspace/u";
        }
        System.out.print("order:" + order);
        System.out.print("selflink:" + "redirect:/u/" + username + "/blogs?order=" + order);
        return "/userspace/u";
    }

    @GetMapping("{username}/blogs/{id}")
    public String listBlogsByOrder(@PathVariable("id") Long id) {
        System.out.print("blogId:" + id);
        return "/userspace/blog";
    }

    @GetMapping("/{username}/blogs/edit")
    public String editBlog() {
        return "userspace/blogedit";
    }



    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userDetailsService;

    @Value("${file.server.url}")
    private String fileServerUrl;



    /**
     * 获取编辑个人主页页面
     * @param usernname
     * @param model
     * @return
     */
    @GetMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView profile(@PathVariable("username") String username, Model model) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("fileServerUrl", fileServerUrl);
        return new ModelAndView("/userspace/profile", "userModel", model);
    }

    /**
     * 更改个人主页资料
     * @param username
     * @param user
     * @return
     */
    @PostMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public String saveProfile(@PathVariable("username") String username, User user) {
        User originalUser = userService.getUserById(user.getId()).get();
        originalUser.setEmail(user.getEmail());
        originalUser.setName(user.getName());

        String rawPassword = originalUser.getPassword();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(user.getPassword());
        boolean isMatch = encoder.matches(rawPassword, encodePasswd);
    if (!isMatch) {
        originalUser.setEncodePassword(user.getPassword());
    }

    userService.saveOrUpdateUser(originalUser);
    return "redirect:/u/" + username + "/profile";
    }

    /**
     * 获取编辑头像页面
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}/acatar")
    @PreAuthorize("authentication.equals(#username)")
    public ModelAndView avatar(@PathVariable("username") String username, Model model) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return new ModelAndView("/userspace/avatar", "userModel", model);
    }

    @PostMapping("/{username}/acatar")
    @PreAuthorize("authentication.equals(#username)")
    public ResponseEntity savaAvatar(@PathVariable("username") String username, @RequestBody User user) {
        String avatarUrl = user.getAvatar();
        User originalUser = userService.getUserById(user.getId()).get();
        originalUser.setAvatar(avatarUrl);
        userService.saveOrUpdateUser(originalUser);

        return ResponseEntity.ok().body(new Response(true, "处理成功", avatarUrl));
    }
}
